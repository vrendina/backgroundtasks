package software.level.backgroundtasks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {

    public static final String TAG = AsyncTaskActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        progressBar = (ProgressBar) findViewById(R.id.pb_task);
        responseTextView = (TextView) findViewById(R.id.tv_response);

    }
    
    public void startTask(View view) {
        Log.d(TAG, "startTask: Starting AsyncTask");
        Toast.makeText(this, "Starting AsyncTask", Toast.LENGTH_SHORT).show();

        progressBar.setVisibility(View.VISIBLE);

        FakeAsyncTask fakeTask = new FakeAsyncTask();
        fakeTask.execute();
    }
    
    public void finishTask(String response) {
        Log.d(TAG, "finishTask: Completed AsyncTask -- " + response);
        Toast.makeText(this, "Completed AsyncTask -- " + response, Toast.LENGTH_SHORT).show();

        responseTextView.setText(response);
        progressBar.setVisibility(View.GONE);
    }

    private class FakeAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return BackgroundTaskUtil.fakeLongOperation();
        }

        @Override
        protected void onPostExecute(String response) {
            finishTask(response);
        }
    }
}
