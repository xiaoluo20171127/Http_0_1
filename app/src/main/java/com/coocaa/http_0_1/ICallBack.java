package com.coocaa.http_0_1;

import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author LEGION XiaoLuo
 * @description
 * @created on 2019/12/12
 */
public interface ICallBack<T> {
    void success(T result);
    void onFailure(Exception e);
    T parse(HttpURLConnection connection) throws IOException, JSONException;
}
