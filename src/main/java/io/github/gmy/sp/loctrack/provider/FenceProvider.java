package io.github.gmy.sp.loctrack.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.gmy.sp.loctrack.DTO.CircleFenceDTO;
import okhttp3.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;


import java.io.IOException;

/**
 * @authon GMY
 * @create 2020-06-12 23:33
 */
@Component
public class FenceProvider {
    public String createCircleFence(CircleFenceDTO circleFenceDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        System.out.println(JSON.toJSONString(circleFenceDTO));
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(circleFenceDTO));
        Request request = new Request.Builder()
                .url(" https://restapi.amap.com/v4/geofence/meta?key=b6aa0e3989f12c7f1240df5676e0538e")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String s = response.body().string();
            System.out.println(s);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFence(String fenceName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(" https://restapi.amap.com/v4/geofence/meta?key=b6aa0e3989f12c7f1240df5676e0538e")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();

//            GIthubUser gIthubUser = JSON.parseObject(string, GIthubUser.class);
//            return gIthubUser;
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
