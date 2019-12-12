package com.coocaa.http_0_1;

import java.util.Map;

/**
 * @author LEGION XiaoLuo
 * @description
 * @created on 2019/12/12
 */
public class Request {
    public ICallBack mICallBack;

    public void setICallBack(ICallBack iCallBack) {
        this.mICallBack = iCallBack;
    }

    public enum RequestMethod {GET, POST, PUT, DELETE}

    public String url;
    public String content;
    public Map<String, String> headers;

    public RequestMethod mMethod;

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.mMethod = method;
    }

    public Request(String url) {
        this(url, RequestMethod.GET);
    }
}
