package com.cyj.compiler;


import com.android.annotation.AutoAssign;
import com.cyj.compiler.util.ParamTypeKinds;
import com.cyj.compiler.util.ParamTypeUtils;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by chengyijun on 18/7/4.
 */
@AutoService(Processor.class)
public class AutoAssiginProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Filer mFiler;
    private Types types;
    private ParamTypeUtils paramTypeUtils;
    private Map<TypeElement, List<Element>> fieldMaps = new HashMap<>();
    private Map<String, Boolean> hashMaps = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        paramTypeUtils = new ParamTypeUtils(types, elementUtils);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> ret = new HashSet<>();
        ret.add(AutoAssign.class.getCanonicalName());
        return ret;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> autoAssigns = roundEnv.getElementsAnnotatedWith(AutoAssign.class);
        try {
            collectData(autoAssigns);
        } catch (Exception e) {
        }
        ParameterSpec objectParamSpec = ParameterSpec.builder(TypeName.OBJECT, "target").build();

        if (fieldMaps != null && fieldMaps.size() > 0) {
            for (Map.Entry<TypeElement, List<Element>> entry : fieldMaps.entrySet()) {
                MethodSpec.Builder autoAssignMethodBuilder = MethodSpec.methodBuilder("autoAssign")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.STATIC)
                        .addParameter(objectParamSpec);

                TypeElement parent = entry.getKey();
                List<Element> children = entry.getValue();

                String qualifiedName = parent.getQualifiedName().toString();
                String packageName = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
                String fileName = parent.getSimpleName() + "_AutoAssign";

                TypeSpec.Builder helper = TypeSpec.classBuilder(fileName)
                        .addModifiers(Modifier.PUBLIC);

                autoAssignMethodBuilder.addStatement("$T realObject = ($T)target", ClassName.get(parent),
                        ClassName.get(parent));
                for (Element element : children) {
                    AutoAssign fieldConfig = element.getAnnotation(AutoAssign.class);
                    String fieldName = element.getSimpleName().toString();
                    String originalValue = "realObject." + fieldName;
                    String statement = "realObject." + fieldName + " = realObject.";
                    statement += "getIntent().";
                    statement = buildStatement(originalValue, statement, paramTypeUtils.typeExchange(element), true);
                    autoAssignMethodBuilder.addStatement(statement, fieldName);
                }
                helper.addMethod(autoAssignMethodBuilder.build());
                try {
                    if (!hashMaps.containsKey(fileName) || !hashMaps.get(fileName)) {
                        JavaFile.builder(packageName, helper.build()).build().writeTo(mFiler);
                    }
                    hashMaps.put(fileName, true);
                } catch (Exception e) {
                }
            }
            return true;
        }
        return false;
    }

    private String buildStatement(String originalValue, String statement, int type, boolean isActivity) {
        if (type == ParamTypeKinds.BOOLEAN.ordinal()) {
            statement += (isActivity ? ("getBooleanExtra($S, " + originalValue + ")") : ("getBoolean($S)"));
        } else if (type == ParamTypeKinds.BYTE.ordinal()) {
            statement += (isActivity ? ("getByteExtra($S, " + originalValue + "") : ("getByte($S)"));
        } else if (type == ParamTypeKinds.SHORT.ordinal()) {
            statement += (isActivity ? ("getShortExtra($S, " + originalValue + ")") : ("getShort($S)"));
        } else if (type == ParamTypeKinds.INT.ordinal()) {
            statement += (isActivity ? ("getIntExtra($S, " + originalValue + ")") : ("getInt($S)"));
        } else if (type == ParamTypeKinds.LONG.ordinal()) {
            statement += (isActivity ? ("getLongExtra($S, " + originalValue + ")") : ("getLong($S)"));
        } else if (type == ParamTypeKinds.CHAR.ordinal()) {
            statement += (isActivity ? ("getCharExtra($S, " + originalValue + ")") : ("getChar($S)"));
        } else if (type == ParamTypeKinds.FLOAT.ordinal()) {
            statement += (isActivity ? ("getFloatExtra($S, " + originalValue + ")") : ("getFloat($S)"));
        } else if (type == ParamTypeKinds.DOUBLE.ordinal()) {
            statement += (isActivity ? ("getDoubleExtra($S, " + originalValue + ")") : ("getDouble($S)"));
        } else if (type == ParamTypeKinds.STRING.ordinal()) {
            statement += (isActivity ? ("getStringExtra($S)") : ("getString($S)"));
        } else if (type == ParamTypeKinds.PARCELABLE.ordinal()) {
            statement += (isActivity ? ("getParcelableExtra($S)") : ("getParcelable($S)"));
        } else if (type == ParamTypeKinds.SERIALIZABLE.ordinal()) {
            statement += (isActivity ? ("getSerializableExtra($S)") : ("getSerializable($S)"));
        }

        return statement;
    }

    private void collectData(Set<? extends Element> elements) throws IllegalAccessException {
        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();

                if (element.getModifiers().contains(Modifier.PRIVATE)) {
                    throw new IllegalAccessException("The autoAssign fields mustn't be decorated with private !!! please check field ["
                            + element.getSimpleName() + "] in class [" + enclosingElement.getQualifiedName() + "]");
                }

                if (fieldMaps.containsKey(enclosingElement)) {
                    fieldMaps.get(enclosingElement).add(element);
                } else {
                    List<Element> children = new ArrayList<>();
                    children.add(element);
                    fieldMaps.put(enclosingElement, children);
                }
            }
        }
    }


}
