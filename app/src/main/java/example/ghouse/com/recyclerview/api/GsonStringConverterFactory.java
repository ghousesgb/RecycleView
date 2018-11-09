package example.ghouse.com.recyclerview.api;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * @author Ghouse on 11/8/16.
 *
 */

public class GsonStringConverterFactory extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if(String.class.equals(type)) {
            return new Converter<String, RequestBody>() {
                @Override
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value);
                }
            };
        }

        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }
}