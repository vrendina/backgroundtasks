package software.level.backgroundtasks;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static final String TAG = AsyncTaskLoaderActivity.class.getSimpleName();

    // A unique identifier for the AsyncTask loader
    private static final int TASK_LOADER_ID = 0;

    private ProgressBar progressBar;
    private Button startButton;
    private TextView responseTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_loader);
        
        progressBar = (ProgressBar) findViewById(R.id.pb_task);
        startButton = (Button) findViewById(R.id.btn_start_task);
        responseTextView = (TextView) findViewById(R.id.tv_response);

        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }

    public void startTask(View view) {
        Log.d(TAG, "startTask: Starting AsyncTask Loader");
        Toast.makeText(this, "Starting AsyncTask Loader", Toast.LENGTH_SHORT).show();

        startButton.setEnabled(false);
        responseTextView.setText(getString(R.string.response));
        progressBar.setVisibility(View.VISIBLE);

        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
        getSupportLoaderManager().getLoader(TASK_LOADER_ID).forceLoad();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: Called onCreateLoader");

        return new AsyncTaskLoader<String>(this) {

            @Override
            protected void onStartLoading() {
                Log.d(TAG, "onStartLoading: Called onStartLoading");
            }

            @Override
            public String loadInBackground() {
                Log.d(TAG, "loadInBackground: Called loadInBackground");

                return BackgroundTaskUtil.fakeLongOperation();
            }

            @Override
            protected void onStopLoading() {
                Log.d(TAG, "onStopLoading: Called onStopLoading");
                super.onStopLoading();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String response) {

        Log.d(TAG, "onLoadFinished: Completed AsyncTask -- " + response);
        Toast.makeText(this, "Completed AsyncTask -- " + response, Toast.LENGTH_SHORT).show();
        
        responseTextView.setText(response);
        progressBar.setVisibility(View.GONE);
        startButton.setEnabled(true);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(TAG, "onLoaderReset: Called onLoaderReset");
    }
}
