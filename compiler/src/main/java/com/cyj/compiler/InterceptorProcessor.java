package com.cyj.compiler;


import com.android.annotation.Interceptor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import static com.squareup.javapoet.JavaFile.builder;

/**
 * Created by liuzhao on 2017/9/20.
 */

@SupportedOptions(Constant.KEY_MODULE_NAME)
@AutoService(Processor.class)
public class InterceptorProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Messager mMessager;
    private String moduleName = "";
    private Filer mFiler;
    private boolean hasDone = false;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
        Map<String, String> options = processingEnv.getOptions();
        if (options != null && options.size() > 0) {
            moduleName = options.get(Constant.KEY_MODULE_NAME);
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> ret = new HashSet<>();
        ret.add(Interceptor.class.getCanonicalName());
        return ret;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> interceptors = roundEnv.getElementsAnnotatedWith(Interceptor.class);
        TypeSpec type = null;
        try {
            type = getInterceptorInitializer(interceptors);
        } catch (Exception e) {
            error("wrong:" + e.getMessage());
        }

        if (type != null && !hasDone) {
            try {
                builder(Constant.AutoCreateInterceptorPackage, type).build().writeTo(mFiler);
                hasDone = true;
            } catch (Exception e) {
                error("wrong!!:" + e.getMessage());
            }
        }

        return true;
    }

    private TypeSpec getInterceptorInitializer(Set<? extends Element> interceptorElements) throws ClassNotFoundException {
        ClassName interceptor = ClassName.get(Constant.DISPATCHER_PACKAGE, "IInterceptor");
        ClassName list = ClassName.get("java.util", "List");
        TypeName interceptorList = ParameterizedTypeName.get(list, interceptor);

        MethodSpec.Builder routerInitBuilder = MethodSpec
                .methodBuilder("initModuleInterceptor")
                .returns(interceptorList)
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC);
        routerInitBuilder.addStatement("List<IInterceptor> list = new java.util.ArrayList()");
        for (Element element : interceptorElements) {
            routerInitBuilder.addStatement("list.add(new $T())",
                    ClassName.get((TypeElement) element));
        }
        routerInitBuilder.addStatement("return list");

        return TypeSpec.classBuilder(Constant.AutoCreateInterceptorPrefix + moduleName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(routerInitBuilder.build())
                .build();
    }

    private void error(String error) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, error);
    }

}
