package com.coocaa.http_0_1;

import android.os.AsyncTask;

import java.io.IOException;

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
            String result = HttpUrlConnectionUtil.execute(mRequest);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof String) {
            mRequest.mICallBack.success((String) o);
        }else{
            mRequest.mICallBack.onFailure((Exception) o);
        }
    }
}
