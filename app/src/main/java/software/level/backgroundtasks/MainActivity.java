package software.level.backgroundtasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void switchActivity(View view) {

        Log.d(TAG, "switchActivity: Called switchActivity");

        Intent intent = null;
        switch(view.getId()) {
            case R.id.btn_async_task:
                intent = new Intent(this, AsyncTaskActivity.class);
                break;
        }

        if(intent != null) {
            startActivity(intent);
        }
    }
}
