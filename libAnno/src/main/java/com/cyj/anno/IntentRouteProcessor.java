package com.cyj.arouter.compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by chengyijun on 18/6/26.
 */

@SupportedAnnotationTypes("com.cyj.anno.IntentRoute")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class IntentRouteProcessor extends AbstractProcessor {

    private Elements elementUtil;
    private Types typesUtil = null;
    private TypeMirror iInterceptor = null;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtil = processingEnvironment.getElementUtils();
        typesUtil = processingEnvironment.getTypeUtils();
        iInterceptor = elementUtil.getTypeElement("").asType();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set != null) {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(IntentRoute.class);
            if (elements != null) {
                for (Element element : elements) {
                    IntentRoute route = element.getAnnotation(IntentRoute.class);


                }
            }
        }

        return false;
    }
}
