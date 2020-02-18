package com.vitesse.group.vghomedecor.ui.network;

import com.vitesse.group.vghomedecor.ui.models.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("login/{emailid}/{dummyvalue}/{password}")
    Call<ResponseObject> login(@Path("emailid") String emailid, @Path("dummyvalue") String dummyvalue , @Path("password") String password);

    @GET("register/{emailid}/{mobile}/{password}")
    Call<ResponseObject> register(@Path("emailid") String emailid, @Path("mobile") String mobile ,@Path("password") String password);
}
