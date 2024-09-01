package com.liangyonghui.rhineapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.liangyonghui.rhineapiclientsdk.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.HashMap;
import java.util.Map;

import static com.liangyonghui.rhineapiclientsdk.utils.SignUtils.genSign;


public class RhineApiClient {

    private String accessKey;
    private String secretKey;

    public RhineApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 调用第三方接口的客户端
     *
     * @author yupi
     */
    public String getNameByGet(String name) {
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        // 将"name"参数添加到映射中
        paramMap.put("name", name);
        // 使用HttpUtil工具发起GET请求，并获取服务器返回的结果
        String result = HttpUtil.get("http://localhost:8123/name/", paramMap);
        // 打印服务器返回的结果
        System.out.println(result);
        // 返回服务器返回的结果
        return result;
    }

    // 使用POST方法从服务器获取名称信息
    public String getNameByPost(@RequestParam String name) {
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        // 使用HttpUtil工具发起POST请求，并获取服务器返回的结果
        String result = HttpUtil.post("http://localhost:8123/name/getNameByPost/", paramMap);
        System.out.println(result);
        return result;
    }

    private Map<String, String> getHeaderMap(String body){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 注意：不能直接发送密钥
        //hashMap.put("secretKey", secretKey);
        // 生成随机数(生成一个包含100个随机数字的字符串)
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        // 请求体内容
        hashMap.put("body", body);
        // 当前时间戳
        // System.currentTimeMillis()返回当前时间的毫秒数。通过除以1000，可以将毫秒数转换为秒数，以得到当前时间戳的秒级表示
        // String.valueOf()方法用于将数值转换为字符串。在这里，将计算得到的时间戳（以秒为单位）转换为字符串
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 生成签名
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }

    // 使用POST方法向服务器发送User对象，并获取服务器返回的结果
    public String getUserNameByPost(@RequestBody User user) {
        // 将User对象转换为JSON字符串
        String json = JSONUtil.toJsonStr(user);
        // 使用HttpRequest工具发起POST请求，并获取服务器的响应
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/name/user")
                // 添加前面构造的请求头
                .addHeaders(getHeaderMap(json))
                .body(json) // 将JSON字符串设置为请求体
                .execute(); // 执行请求
        // 打印服务器返回的状态码
        System.out.println(httpResponse.getStatus());
        // 获取服务器返回的结果
        String result = httpResponse.body();
        // 打印服务器返回的结果
        System.out.println(result);
        // 返回服务器返回的结果
        return result;
    }
}
