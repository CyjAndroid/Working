package com.cyj.compiler.util;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.cyj.compiler.Constant.*;
import static com.cyj.compiler.Constant.BOOLEAN;
import static com.cyj.compiler.Constant.BYTE;
import static com.cyj.compiler.Constant.DOUBEL;
import static com.cyj.compiler.Constant.FLOAT;
import static com.cyj.compiler.Constant.INTEGER;
import static com.cyj.compiler.Constant.LONG;
import static com.cyj.compiler.Constant.SHORT;
import static com.cyj.compiler.Constant.STRING;
import static com.cyj.compiler.Constant.BYTE;

/**
 * Created by liuzhao on 2017/10/9.
 */
public class ParamTypeUtils {

    private Types types;
    private Elements elements;

    public ParamTypeUtils(Types types, Elements elements) {
        this.types = types;
        this.elements = elements;
    }

    /**
     * Diagnostics out the true java type
     *
     * @param element Raw type
     * @return Type class of java
     */
    public int typeExchange(Element element) {
        TypeMirror typeMirror = element.asType();

        // Primitive
        if (typeMirror.getKind().isPrimitive()) {
            return element.asType().getKind().ordinal();
        }

        switch (typeMirror.toString()) {
            case BYTE:
                return ParamTypeKinds.BYTE.ordinal();
            case SHORT:
                return ParamTypeKinds.SHORT.ordinal();
            case INTEGER:
                return ParamTypeKinds.INT.ordinal();
            case LONG:
                return ParamTypeKinds.LONG.ordinal();
            case FLOAT:
                return ParamTypeKinds.FLOAT.ordinal();
            case DOUBEL:
                return ParamTypeKinds.DOUBLE.ordinal();
            case BOOLEAN:
                return ParamTypeKinds.BOOLEAN.ordinal();
            case STRING:
                return ParamTypeKinds.STRING.ordinal();
            case PARCELABLE:
                return ParamTypeKinds.PARCELABLE.ordinal();
            case SERIALIZABLE:
                return ParamTypeKinds.SERIALIZABLE.ordinal();
            default:
                return ParamTypeKinds.OBJECT.ordinal();
        }
    }
}
