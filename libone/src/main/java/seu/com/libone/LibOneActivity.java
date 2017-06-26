package seu.com.libone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LibOneActivity extends AppCompatActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, LibOneActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_one);
    }
}
