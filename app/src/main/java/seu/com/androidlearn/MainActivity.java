package seu.com.androidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.statusbar.StatusBarTestActivity;
import seu.com.androidlearn.touch.TouchTestActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnTouchEvent)
    Button btnTouchEvent;
    @BindView(R.id.btnStatusBar)
    Button btnStatusBar;

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
    @OnClick(R.id.btnStatusBar)
    protected void clickStatusBarButton() {
        StatusBarTestActivity.launch(this);
    }
}
