package com.coocaa.http_0_1;

import android.content.res.Resources;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author LEGION XiaoLuo
 * @description
 * @created on 2019/12/12
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {
    private Request mRequest;

    public RequestTask(Request request) {
        this.mRequest = request;
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            HttpURLConnection connection = HttpUrlConnectionUtil.execute(mRequest);
            return mRequest.mICallBack.parse(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception) {
            mRequest.mICallBack.onFailure((Exception) o);
        }else{
            mRequest.mICallBack.success(o);
        }
    }
}
