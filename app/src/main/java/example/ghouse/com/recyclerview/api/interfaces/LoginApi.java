package example.ghouse.com.recyclerview.api.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * @author Ghouse on 11/8/16.
 *
 */
public interface LoginApi {
    @GET("movie/top_rated")
    Call<ResponseBody> getMovie(@Query("api_key") String apiKey);

 }


