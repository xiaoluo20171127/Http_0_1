package com.coocaa.http_0_1;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

/**
 * @author LEGION XiaoLuo
 * @description
 * @created on 2019/12/12
 */
public abstract class AbstractCallBack<T> implements ICallBack<T> {

    @Override
    public T parse(HttpURLConnection connection) throws IOException, JSONException {
        int status = connection.getResponseCode();
        if (status == 200) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int len;
            while ((len = is.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            is.close();
            out.flush();
            out.close();
            String result = new String(out.toByteArray());
            return bindData(result);
        }
        return null;
    }
    /**
     * @param result 网络请求返回值
     * @return T 转化类型
     *
     * */
    public abstract T bindData(String result) throws JSONException;
}
