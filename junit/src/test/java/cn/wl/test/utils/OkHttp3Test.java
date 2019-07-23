package cn.wl.test.utils;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

public class OkHttp3Test {

    private String baiduUrl = "https://www.baidu.com";

    @Test
    public void testOkHttpGetRequest() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baiduUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOkHttpPostRequest() {
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(baiduUrl)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
