package com.coocaa.http_0_1;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author LEGION XiaoLuo
 * @description
 * @created on 2019/12/12
 */
public class HttpUrlConnectionUtil {

    public static HttpURLConnection execute(Request request) throws IOException {
        Request.RequestMethod method = request.mMethod;
        switch (method) {
            case PUT:
            case GET: {
               return get(request);
            }
            case DELETE:
            case POST: {
               return post(request);
            }
            default:
                break;
        }
        return null;
    }

    private static HttpURLConnection get(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.mMethod.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);

        addHeader(connection, request.headers);

        int status = connection.getResponseCode();

        return connection;
    }

    private static HttpURLConnection post(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.mMethod.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);
        connection.setDoOutput(true);


        addHeader(connection, request.headers);

        OutputStream os = connection.getOutputStream();
        os.write(request.content.getBytes());

        int status = connection.getResponseCode();

        return connection;
    }

    private static void addHeader(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null || headers.size() == 0) {
            return;
        }

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }


}
