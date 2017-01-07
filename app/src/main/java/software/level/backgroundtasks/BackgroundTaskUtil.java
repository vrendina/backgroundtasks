package software.level.backgroundtasks;

import android.util.Log;

public class BackgroundTaskUtil {

    public static final String TAG = BackgroundTaskUtil.class.getSimpleName();

    // How long to perform the "task" for in milliseconds
    public static final long DURATION = 5000;

    public static String fakeLongOperation() {
        try {
            Thread.sleep(DURATION);
        } catch (InterruptedException e) {
            Log.e(TAG, "fakeLongOperation: ", e);
        }

        return "Task completed!";
    }

}
