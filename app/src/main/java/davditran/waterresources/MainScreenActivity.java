package davditran.waterresources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenActivity extends AppCompatActivity {

    //UI References
    private Button submitReportsButton;
    private Button userProfileButton;
    private Button viewReportsButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        submitReportsButton = (Button) findViewById(R.id.submitReportButton);
        userProfileButton = (Button) findViewById(R.id.userProfileButton);
        viewReportsButton = (Button) findViewById(R.id.viewReportsButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
