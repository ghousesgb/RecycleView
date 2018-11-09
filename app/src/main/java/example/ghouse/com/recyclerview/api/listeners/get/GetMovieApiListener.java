package example.ghouse.com.recyclerview.api.listeners.get;

import example.ghouse.com.recyclerview.api.pojos.GetMovieResponse;

/**
 * @author Ghouse on 11/8/16.
 *
 */
public interface GetMovieApiListener {
    void onDoneApiCall(GetMovieResponse getMovieResponse);
    void onFailApiCall();
}
