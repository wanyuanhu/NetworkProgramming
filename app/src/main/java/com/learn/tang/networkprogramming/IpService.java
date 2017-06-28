package com.learn.tang.networkprogramming;


import com.learn.tang.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tang on 2017/6/28.
 */

public interface IpService {
    @GET ("getIpInfo.php")
    Call<IpModel> getIpMsg(@Query("ip") String ip);
}
