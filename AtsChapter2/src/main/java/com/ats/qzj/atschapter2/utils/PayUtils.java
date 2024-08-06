package com.ats.qzj.atschapter2.utils;

import okhttp3.*;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PayUtils {
    OkHttpClient client;
    Request request;

    //创建okhhtpclient对象
    @BeforeClass
    public void statement() {
        client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS) // 连接超时设置
                .readTimeout(30, TimeUnit.SECONDS)    // 读取超时设置
                .writeTimeout(30, TimeUnit.SECONDS)// 写入超时设置;
                .build();
    }

    //发送post-json请求并获取响应
    public Response exePostReqJson(String url,
                               RequestBody requestBody,
                               Headers headers) throws IOException {
                request = new Request.Builder().url(url).post(requestBody).headers(headers).build();
        Response execute = client.newCall(request).execute();
        return execute;
    }

    //重载,发送post-form请求并获取响应
    public Response exePostReqJson(String url,
                               FormBody formBody,
                               Headers headers) throws IOException {
        request = new Request.Builder().url(url).post(formBody).headers(headers).build();
        Response execute = client.newCall(request).execute();
        return execute;
    }

    //发送get请求并获取响应
    public Response exeGetReq(String getUrl) throws IOException {
        // 直接构建 URL 并创建请求
        Request getRequest = new Request.Builder()
                .url(getUrl)
                .build();
        Response getExecute = client.newCall(getRequest).execute();
        return getExecute;
    }
}
