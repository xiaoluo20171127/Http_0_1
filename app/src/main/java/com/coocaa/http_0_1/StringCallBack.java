package com.coocaa.http_0_1;

import org.json.JSONException;

/**
 * @ Created on: 2019/12/16
 * @Author: LEGION XiaoLuo
 * @ Description:
 */
public abstract class StringCallBack extends AbstractCallBack {
    @Override
    public String bindData(String result) throws JSONException {
        return result;
    }
}
