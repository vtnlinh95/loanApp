package Base;

import java.util.List;

import Entity.LoanEntity;
import Entity.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by admin on 4/28/18.
 */

public interface ApiInterface {
    @GET("/offer")
    Call<LoanEntity> getLoanInfo();

    @GET("/provinces")
    Call<List<String>> getProvincesList();

    @POST("/loans")
    Call<UserEntity> registerUser(@Body UserEntity user);

}
