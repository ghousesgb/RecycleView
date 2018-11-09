package example.ghouse.com.recyclerview.api.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import example.ghouse.com.recyclerview.api.RestClient;
import example.ghouse.com.recyclerview.api.interfaces.LoginApi;
import example.ghouse.com.recyclerview.api.listeners.get.GetMovieApiListener;
import example.ghouse.com.recyclerview.api.pojos.GetMovieResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Ghouse on 11/8/16.
 */
public class MovieApiManager {

    public static void getMovies(GetMovieApiListener listener) {
        final Retrofit retrofit = RestClient.getInitializedRestAdapterWithOutAuthorizationHeader();
        Call<ResponseBody> call = retrofit.create(LoginApi.class).getMovie("7e8f60e325cd06e164799af1e317d7a7");
        try {
            Response<ResponseBody> response = call.execute();
            if (response.isSuccessful()) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject != null) {
                        Gson gson = new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation()
                                .registerTypeAdapter(long.class, new JsonDeserializer<Long>() {
                                    @Override
                                    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                        long value = 0;
                                        try {
                                            value = json.getAsLong();
                                        } catch (NumberFormatException e) {
                                        }
                                        return value;
                                    }
                                })
                                .registerTypeAdapter(int.class, new JsonDeserializer<Integer>() {
                                    @Override
                                    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                        int value = 0;
                                        try {
                                            value = json.getAsInt();
                                        } catch (NumberFormatException e) {
                                        }
                                        return value;
                                    }
                                })
                                .registerTypeAdapter(float.class, new JsonDeserializer<Float>() {
                                    @Override
                                    public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                        float value = 0;
                                        try {
                                            value = json.getAsFloat();
                                        } catch (NumberFormatException e) {
                                        }
                                        return value;
                                    }
                                })
                                .create();
                        GetMovieResponse getMovieResponse = gson.fromJson(jsonObject.toString(), GetMovieResponse.class);
                        listener.onDoneApiCall(getMovieResponse);
                    } else
                        listener.onFailApiCall();
                } catch (JSONException e) {
                    listener.onFailApiCall();
                } catch (IOException e) {
                    listener.onFailApiCall();
                }
            } else {
                listener.onFailApiCall();
            }
        } catch (IOException e) {
            listener.onFailApiCall();
        }
    }
}
