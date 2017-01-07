package software.level.backgroundtasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableActivity extends AppCompatActivity {

    public static final String TAG = ObservableActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private TextView responseTextView;

    private Single taskSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);

        progressBar = (ProgressBar) findViewById(R.id.pb_task);
        responseTextView = (TextView) findViewById(R.id.tv_response);

        taskSingle = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {
                e.onSuccess(BackgroundTaskUtil.fakeLongOperation());
            }
        })      .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public void startTask(View view) {
        Log.d(TAG, "startTask: Starting Observable");
        Toast.makeText(this, "Starting Observable", Toast.LENGTH_SHORT).show();

        responseTextView.setText(getString(R.string.response));
        progressBar.setVisibility(View.VISIBLE);

        taskSingle.subscribeWith(getSingleObserver());
    }

    public SingleObserver<String> getSingleObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);
                Toast.makeText(ObservableActivity.this, "Completed Observable -- " + response, Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.GONE);
                responseTextView.setText(response);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }
        };
    }

}
