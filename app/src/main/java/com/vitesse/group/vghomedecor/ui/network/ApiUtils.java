package com.vitesse.group.vghomedecor.ui.network;

public class ApiUtils {
    public static final String BASE_URL = /*"http://192.168.20.110/demo/"*/"https://o6t7laaumi.execute-api.us-east-2.amazonaws.com/vdesign1/";

    public static RetrofitService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(RetrofitService.class);
    }
}
