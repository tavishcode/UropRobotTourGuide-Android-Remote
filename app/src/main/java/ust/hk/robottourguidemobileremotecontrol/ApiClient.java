package ust.hk.robottourguidemobileremotecontrol;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by tavish on 2/15/17.
 */

public class ApiClient {

    private static final String baseUrl= "10.89.22.206";
    private static final int port=8000;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static Call moveForward()
    {
        HttpUrl httpUrl= new HttpUrl.Builder()
                .scheme("http")
                .host(baseUrl)
                .port(port)
                .addPathSegment("forward")
                .build();
        Request request= new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        return OkHttpSingleton.getOkHttpInstance().getOkHttpClient().newCall(request);
    }
    public static Call moveBack()
    {
        HttpUrl httpUrl= new HttpUrl.Builder()
                .scheme("http")
                .host(baseUrl)
                .port(port)
                .addPathSegment("back")
                .build();
        Request request= new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        return OkHttpSingleton.getOkHttpInstance().getOkHttpClient().newCall(request);
    }
    public static Call moveLeft()
    {
        HttpUrl httpUrl= new HttpUrl.Builder()
                .scheme("http")
                .host(baseUrl)
                .port(port)
                .addPathSegment("left")
                .build();
        Request request= new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        return OkHttpSingleton.getOkHttpInstance().getOkHttpClient().newCall(request);
    }
    public static Call moveRight()
    {
        HttpUrl httpUrl= new HttpUrl.Builder()
                .scheme("http")
                .host(baseUrl)
                .port(port)
                .addPathSegment("right")
                .build();
        Request request= new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        return OkHttpSingleton.getOkHttpInstance().getOkHttpClient().newCall(request);
    }
    public static Call stop()
    {
        HttpUrl httpUrl= new HttpUrl.Builder()
                .scheme("http")
                .host(baseUrl)
                .port(port)
                .addPathSegment("stop")
                .build();
        Request request= new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        return OkHttpSingleton.getOkHttpInstance().getOkHttpClient().newCall(request);
    }
    public static Call sendLocation(String location)
    {
        HttpUrl httpUrl= new HttpUrl.Builder()
                .scheme("http")
                .host(baseUrl)
                .port(port)
                .addPathSegment("sendLocation")
                .build();
        RequestBody reqBody= RequestBody.create(JSON,location);
        Request request= new Request.Builder()
                .post(reqBody)
                .url(httpUrl)
                .build();
        return OkHttpSingleton.getOkHttpInstance().getOkHttpClient().newCall(request);
    }
}
