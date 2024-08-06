package com.ats.qzj.atschapter2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ats.qzj.atschapter2.atsService.DataUtilsServ;
import com.ats.qzj.atschapter2.model.SettleEntity;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = AtsChapter2Application.class)
public class Atsouttest extends AbstractTestNGSpringContextTests {
    @Autowired
    private DataUtilsServ dataUtilsServ;
    private OkHttpClient client;
    private RequestBody jsonBody;
    private RequestBody fileBody;
    private FormBody formBody;

    String json = """
            {
                "limit": 10,
                "page": 1,
                "concernedUser": "f5e6d69c79544eec9000f2e2dc4fd4d4",
                "systemEntrance": "3",
                "status": [
                    "J10010",
                    "J10030",
                    "J10005"
                ],
                "name": "地质大数据",
                "consistentBudget": "",
                "category": "",
                "progressStatus": "",
                "projectStatus": "",
                "oaProjectNumber": ""
            }
            """;
    String filedir = "";
    String url = "https://ei-gate.gcnao.cn/api/project/plus/page";
    String authorization = "eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50SWQiOiJkOGI4YjVhYzkyMGY0YjkxYTEwY2Y3MDJlMDZlMmFiZSIsIm5hbWUiOiLnjovnkpAiLCJ1c2VybmFtZSI6IndhbmdsdSIsInBob25lTnVtYmVyIjoiMTgwODY0OTQyNTYiLCJhY2NvdW50VHlwZSI6IlBFUlNPTkFMIiwidXNlcklkIjoiZDFmZTU2ZTY0YTBiNDg0Zjk4MTZhOTBlNWFkOGJhYmIiLCJjb21wYW55SWQiOiI1NzQzOGQ2NDc5ZjU0ZTNkYjUzY2EwYWZjNmYxYzk2ZSIsImNvbXBhbnlOYW1lIjoi5LqR5Z-65pm65oWn5bel56iL6IKh5Lu95pyJ6ZmQ5YWs5Y-4IiwianRpLXV1aWQiOiJqdGktNjIzMGM3ZjItN2I3YS00MGJhLTllODQtMjRhNzc0NGI2NDA0IiwiZGV2ZWxvcFJvbGVOdW1iZXIiOiJjb21tb25BcHBNYW5hZ2VyIiwiZXhwIjoxNzI2OTAyOTEzfQ.0CQGk6lEHFtwPJ624LECgvDNLk6BsVYYqYuayhRLO1I";

    // 关闭 OkHttpClient 实例的线程池
    @AfterClass
    public void closehttp() {
        if (client != null) {
            client.dispatcher().executorService().shutdown(); // 关闭线程池
            // 清理 Request 对象（通常不需要显式关闭 Request 对象）
            // Request 对象是不可变的，不需要关闭
            // 如果有其他的资源需要清理，可以在这里处理
        }
    }

    //存放参数化数据
    @DataProvider(name = "datapv")
    public Object[][] datapv() {
        return new Object[][]{
                {"地质大数据平台", "L10003"},
                {"地质大数据平台", "L10002"}
        };
    }

    @Test
    public void test1(){
        List<SettleEntity> cardDetailsById = dataUtilsServ.findCardDetailsById(128143L);
        System.out.println(cardDetailsById);

    }

    //请求发送并响应

    @Test(dataProvider = "datapv")
    public void testCheckout(String dizhi, String fields) throws IOException {
        //声明okhttpclient对象
        client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS) // 连接超时设置
                .readTimeout(30, TimeUnit.SECONDS)    // 读取超时设置
                .writeTimeout(30, TimeUnit.SECONDS)// 写入超时设置;
                .build();

        //声明header中头信息
        Headers headers = new Headers.Builder()
                .add("authorization", authorization)
                .add("traceid", "uxek5lr2mb")
                .build();

        //声明请求中的媒体类型信息
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");

        //请求方式json，创建请求体数据
        jsonBody = RequestBody.create(json, mediaType);

        //请求方式form，创建请求体数据
        formBody = new FormBody.Builder()
                .add("", "")
                .add("", "").build();

        //请求方式file，创建请求体数据
        fileBody = RequestBody.create(new File(filedir), mediaType);

        //创建请求
        Request result = new Request.Builder()
                .post(jsonBody)
                .url(url)
                .headers(headers).build();

        //发送请求,获取响应
        if (client == null) {
            throw new IllegalStateException("OkHttpClient instance is null");
        }
        Response response = client.newCall(result).execute();

        //处理响应参数转化为jsonObject
        String resjson = response.body().string();
        JSONObject jsonObject = JSON.parseObject(resjson);

        //拿到需要的json对象
        //先拿到第1层的data对象
        JSONObject jsondata = jsonObject.getJSONObject("data");
        //拿到第2层的rows数组
        JSONArray rows = jsondata.getJSONArray("rows");
        //提取数组中第一个对象
        JSONObject firstRow = rows.getJSONObject(0);
        //拿到第3层的name
        String name = firstRow.getString("name");
        //拿到第3曾的field
        String field = firstRow.getString("field");

        //断言结果数据,并参数化
        Assert.assertEquals(name, dizhi, "名称不匹配");
        Assert.assertEquals(field, fields, "文件不匹配");

        // 加入extebtReport并生成报告 - 已优化，生成了testng.xml

    }

    /*
     * 优化：
     * 抽包抽方法
     * 链接数据库
     * */
}
