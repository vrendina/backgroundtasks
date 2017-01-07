package software.level.backgroundtasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {

    public static final String TAG = AsyncTaskActivity.class.getSimpleName();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        progressBar = (ProgressBar) findViewById(R.id.pb_task);

    }
    
    public void startTask(View view) {
        Log.d(TAG, "startTask: Starting AsyncTask");
        Toast.makeText(this, "Starting AsyncTask", Toast.LENGTH_SHORT).show();

        progressBar.setVisibility(View.VISIBLE);

    }
}
