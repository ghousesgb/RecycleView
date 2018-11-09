package example.ghouse.com.recyclerview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Ghouse on 11/8/16.
 */

public class TestService extends Service {
    private static final String TAG = "TestServices";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        int f = intent.getExtras().getInt("flag");
        Log.e(TAG, "onStartCommand: f: "+f);
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
