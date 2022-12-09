package dat.com.QuizzDT;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        textView = findViewById(R.id.info);
        textView.setText("Nhóm phát triển:\n • Nguyễn Trí Thành\n • Trần Văn Đạt\n\nEmail liên hệ:\nuetmemberx@gmail.com");
    }
}
