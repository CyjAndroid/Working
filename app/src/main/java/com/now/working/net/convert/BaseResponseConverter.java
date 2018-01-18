package com.now.working.net.convert;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Cyj on 17/12/29.
 */

public abstract class BaseResponseConverter<T> implements Converter<ResponseBody, T> {
    @Override
    public T convert(ResponseBody value) throws IOException {
        return parserJson(value.string());
    }

    public abstract T parserJson(String json);
}
