package com.cyj.compiler;

import com.android.annotation.Dispatcher;
import com.android.annotation.DispatcherModules;
import com.android.annotation.ModuleService;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static com.squareup.javapoet.JavaFile.builder;


@SupportedOptions(Constant.KEY_MODULE_NAME)
@AutoService(Processor.class)
public class DispatcherProcessor extends AbstractProcessor {
    private Messager mMessager;
    private Filer mFiler;
    private Elements elementUtils;
    private String moduleName = "";
    private Set<String> set;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
        set = new HashSet<String>();

        Map<String, String> options = processingEnv.getOptions();
        if (options != null && !options.isEmpty()) {
            moduleName = options.get(Constant.KEY_MODULE_NAME);
            set.add(moduleName);
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> ret = new HashSet<>();
        ret.add(Dispatcher.class.getCanonicalName());
        ret.add(DispatcherModules.class.getCanonicalName());
        ret.add(ModuleService.class.getCanonicalName());
        return ret;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        Set<? extends Element> elementDispatchers = roundEnv.getElementsAnnotatedWith(Dispatcher.class);
        String[] moduleNames = null;
        Set<? extends Element> modulesList = roundEnv.getElementsAnnotatedWith(DispatcherModules.class);
        Set<? extends Element> elementModuleServices = roundEnv.getElementsAnnotatedWith(ModuleService.class);
        if (modulesList != null && modulesList.size() > 0) {
            Element modules = modulesList.iterator().next();
            moduleNames = modules.getAnnotation(DispatcherModules.class).value();
        }

        try {
            TypeSpec type = getRouterTableInitializer(elementDispatchers,elementModuleServices);
            if (type != null) {
                builder(Constant.DISPATCHER_PACKAGE, type).build().writeTo(mFiler);
            }
            if (moduleNames != null && moduleNames.length > 0) {
                TypeSpec typeInit = generateModulesRouterInit(moduleNames);
                if (typeInit != null) {
                    builder(Constant.DISPATCHER_PACKAGE, typeInit).build().writeTo(mFiler);
                }
            }
        } catch (FilerException e) {
            e.printStackTrace();
        } catch (Exception e) {
//            error(e.getMessage());
        }
        return true;
    }

    private TypeSpec generateModulesRouterInit(String[] moduleNames) {
        //创建方法initActivityDispatcher
        MethodSpec.Builder initActivityDispatcherMethod = MethodSpec.methodBuilder("initActivityDispatcher")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);
        for (String module : moduleNames) {
            initActivityDispatcherMethod.addStatement(Constant.DISPATCHER_PACKAGE+".Dispatcher.getActivityDispatcher().initActivityMaps(new " +
                    Constant.AutoCreateActivityMapPrefix + module + "())");
        }

        MethodSpec.Builder initModuleServiceMethod = MethodSpec.methodBuilder("initModuleService")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);
        for (String module : moduleNames) {
            initModuleServiceMethod.addStatement(Constant.AutoCreateActivityMapPrefix + module + ".initModuleService()");
        }

        MethodSpec.Builder initMethod = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);
        initMethod.addStatement("initActivityDispatcher()")
                .addStatement("initModuleService()");

        return TypeSpec.classBuilder("RouterInit")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(initActivityDispatcherMethod.build())
                .addMethod(initModuleServiceMethod.build())
                .addMethod(initMethod.build())
                .build();
    }


    private TypeSpec getRouterTableInitializer(Set<? extends Element> elements,Set<? extends Element> moduleServiceElements) throws ClassNotFoundException {
        if (elements == null || elements.size() == 0) {
            return null;
        }
        //创建一个名为activityMap的HashMap对象
        ParameterizedTypeName mapTypeName = ParameterizedTypeName
                .get(ClassName.get(HashMap.class), ClassName.get(String.class),
                        ClassName.get(Class.class));
        ParameterSpec mapParameterSpec = ParameterSpec.builder(mapTypeName, "activityMap")
                .build();
        //创建一个initActivityMap方法
        MethodSpec.Builder routerInitBuilder = MethodSpec.methodBuilder("initActivityMap")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(mapParameterSpec);
        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
            }
            Dispatcher router = element.getAnnotation(Dispatcher.class);
            String routerUrls = router.value();
            if (routerUrls != null) {
                routerInitBuilder.addStatement("activityMap.put($S, $T.class)", routerUrls, ClassName.get((TypeElement) element));

//                for (String routerUrl : routerUrls) {
//                    routerInitBuilder.addStatement("activityMap.put($S, $T.class)", routerUrl, ClassName.get((TypeElement) element));
//                }
            }
        }

        MethodSpec.Builder initMethod = MethodSpec.methodBuilder("initModuleService")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
        for (Element element : moduleServiceElements) {
            initMethod.addStatement(Constant.DISPATCHER_PACKAGE+".ModuleServiceManager.register("+Constant.DISPATCHER_PACKAGE+".BaseModuleService.$T.class, new $T()) ",
                    ClassName.get((TypeElement) element), ClassName.get((TypeElement) element));
        }

        TypeElement routerInitializerType = elementUtils.getTypeElement(Constant.DISPATCHER_PACKAGE+".IActivityInitMap");
        return TypeSpec.classBuilder(Constant.AutoCreateActivityMapPrefix + moduleName)
                .addSuperinterface(ClassName.get(routerInitializerType))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(routerInitBuilder.build())
                .addMethod(initMethod.build())
                .build();
    }

}
