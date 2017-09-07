package io.github.abhirojp.myfeed_android.API;

import java.util.ArrayList;
import java.util.List;

import io.github.abhirojp.myfeed_android.data.DataModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by abhiroj on 7/9/17.
 */

public interface MyBackendAPI {

    @GET("?format=json")
    Call<ArrayList<DataModel>> loadData();

}
