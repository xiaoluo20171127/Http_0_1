package com.coocaa.http_0_1;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ Created on: 2019/12/16
 * @Author: LEGION XiaoLuo
 * @ Description:
 */
public abstract class JsonCallBack<T> extends AbstractCallBack<T> {

    @Override
    public T bindData(String result) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        JSONObject data = jsonObject.optJSONObject("data");
        Gson gson = new Gson();
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return gson.fromJson(data.toString(), type);
    }
}
