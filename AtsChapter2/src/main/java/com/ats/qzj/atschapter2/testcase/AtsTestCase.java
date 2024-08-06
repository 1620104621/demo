package com.ats.qzj.atschapter2.testcase;

import com.ats.qzj.atschapter2.utils.PayUtils;
import com.ats.qzj.atschapter2.utils.JsonUtils;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

//执行测试用例

public class AtsTestCase {
    //实例化JsonUtils
    private JsonUtils jsonUtils;
    //实例化Payokhttpclient
    private PayUtils payokhttpclient;

    //准备请求参数
    //ATS-新收银台支付
    private String url = "https://ei-gate.gcnao.cn/api/project/plus/page";
    private String bodyJson = """
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
    String authorization = "eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50SWQiOiJkOGI4YjVhYzkyMGY0YjkxYTEwY2Y3MDJlMDZlMmFiZSIsIm5hbWUiOiLnjovnkpAiLCJ1c2VybmFtZSI6IndhbmdsdSIsInBob25lTnVtYmVyIjoiMTgwODY0OTQyNTYiLCJhY2NvdW50VHlwZSI6IlBFUlNPTkFMIiwidXNlcklkIjoiZDFmZTU2ZTY0YTBiNDg0Zjk4MTZhOTBlNWFkOGJhYmIiLCJjb21wYW55SWQiOiI1NzQzOGQ2NDc5ZjU0ZTNkYjUzY2EwYWZjNmYxYzk2ZSIsImNvbXBhbnlOYW1lIjoi5LqR5Z-65pm65oWn5bel56iL6IKh5Lu95pyJ6ZmQ5YWs5Y-4IiwianRpLXV1aWQiOiJqdGktNjIzMGM3ZjItN2I3YS00MGJhLTllODQtMjRhNzc0NGI2NDA0IiwiZGV2ZWxvcFJvbGVOdW1iZXIiOiJjb21tb25BcHBNYW5hZ2VyIiwiZXhwIjoxNzI2OTAyOTEzfQ.0CQGk6lEHFtwPJ624LECgvDNLk6BsVYYqYuayhRLO1I";
    private Headers headers = new Headers.Builder()
            .add("Content-Type", "text/html; charset=UTF-8")
            .add("authorization", authorization).build();

    //参数化响应校验仓库
    @DataProvider(name = "datav")
    public Object[][] saveData() {
        return new Object[][]{{"", ""}, {"", ""}};
    }

    @Test(dataProvider = "datav")
    public void testCheckout() throws IOException {

        //声明请求类型,并创建装配请求参
        MediaType mediaType = MediaType.parse(Objects.requireNonNull(this.headers.get("contype")));
        RequestBody requestBody = RequestBody.create(bodyJson, mediaType);
        //获取响应
        Response response = payokhttpclient.exePostReqJson(url, requestBody, headers);
        //响应json转化为String对象
        String resJson = response.body().string();
        //调用JsonUtils处理响应json
        List<String> expects = jsonUtils.extractFieldValues(resJson, "填需要校验的响应参用.来代表层级");
        //断言执行方法
        Assert.assertEquals(expects.get(0), "预期结果");
    }


}
