package example.ghouse.com.recyclerview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import example.ghouse.com.recyclerview.api.listeners.get.GetMovieApiListener;
import example.ghouse.com.recyclerview.api.managers.MovieApiManager;
import example.ghouse.com.recyclerview.api.pojos.GetMovieResponse;

/**
 * @author Ghouse on 11/8/16.
 */

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataSet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_screen);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(mDataSet, new OnItemClickListener() {
            @Override
            public void onItemClick(String name) {
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        new FetchMovies().execute();
    }

    public interface OnItemClickListener {
        void onItemClick(String name);
    }

    class FetchMovies extends AsyncTask<String, Void, Void> {
        private ProgressDialog progressDialog;
        private GetMovieResponse mGetMovieResponse;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                progressDialog.setCancelable(false);
        }

        @Override
        protected Void doInBackground(String... params) {
            MovieApiManager.getMovies(new GetMovieApiListener() {
                @Override
                public void onDoneApiCall(GetMovieResponse getMovieResponse) {
                    mGetMovieResponse = getMovieResponse;
                }

                @Override
                public void onFailApiCall() {
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            Log.e("MainActivity", "onPostExecute: mGetMovieResponse size: "+mGetMovieResponse.getResults().size());
        }
    }
}