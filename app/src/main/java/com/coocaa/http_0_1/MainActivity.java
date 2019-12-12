package com.coocaa.http_0_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testHttpPostOnSubThread();
            }
        });
    }

    private void testHttpPostOnSubThread(){
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);
        request.setICallBack(new ICallBack() {
            @Override
            public void success(String result) {
                Log.e("stay", "testHttpGet return:" + result);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        request.content = content;
        RequestTask task = new RequestTask(request);
        task.execute();
    }
}
