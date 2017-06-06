package seu.com.androidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.touch.TouchTestActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnTouchEvent)
    Button btnTouchEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTouchEvent)
    protected void clickTouchButton() {
        TouchTestActivity.launch(this);
    }
}
