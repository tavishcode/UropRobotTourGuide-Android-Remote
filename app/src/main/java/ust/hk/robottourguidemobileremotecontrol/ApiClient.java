package ust.hk.robottourguidemobileremotecontrol;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by tavish on 2/15/17.
 */

public class ApiClient {

    private static final String baseUrl= "10.20.141.114";
    private static final int port=8000;

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

}
