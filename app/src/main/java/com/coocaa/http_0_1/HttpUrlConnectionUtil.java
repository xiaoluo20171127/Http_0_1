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

    public static String execute(Request request) throws IOException {
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

    private static String get(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.mMethod.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);

        addHeader(connection, request.headers);

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
            return new String(out.toByteArray());
        }
        return null;
    }

    private static String post(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.mMethod.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);
        connection.setDoOutput(true);


        addHeader(connection, request.headers);

        OutputStream os = connection.getOutputStream();
        os.write(request.content.getBytes());

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
            return new String(out.toByteArray());
        }
        return null;
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
