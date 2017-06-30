package com.learn.tang.networkprogramming;

import com.learn.tang.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Tang on 2017/6/29.
 */

public interface IpServiceForPost {
    @FormUrlEncoded
    @POST("getIpInfo.ph")
    Call<IpModel> getIpMsg(@Field("ip") String first);
}
