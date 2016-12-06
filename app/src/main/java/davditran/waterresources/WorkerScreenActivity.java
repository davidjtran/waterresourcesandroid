package davditran.waterresources;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

import davditran.waterresources.R;
import davditran.waterresources.model.User;

public class WorkerScreenActivity extends AppCompatActivity {

    //UI References
    private Button submitReportsButton;
    private Button userProfileButton;
    private Button viewReportsButton;
    private Button qualityReportButton;
    private Button viewQualityReportButton;
    private Button mapButton;
    private Button cancelButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_screen);
        this.user = (User) getIntent().getSerializableExtra("User");

        submitReportsButton = (Button) findViewById(R.id.submitReportButton);
        userProfileButton = (Button) findViewById(R.id.userProfileButton);
        viewReportsButton = (Button) findViewById(R.id.viewReportsButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        qualityReportButton = (Button) findViewById(R.id.qualityReportButton);
        viewQualityReportButton = (Button) findViewById(R.id.viewQualityReportButton);
        mapButton = (Button) findViewById(R.id.mapButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userProfileIntent = new Intent(WorkerScreenActivity.this, UserProfileActivity.class);
                userProfileIntent.putExtra("User", (Serializable) WorkerScreenActivity.this.user);
                WorkerScreenActivity.this.startActivity(userProfileIntent);
            }
        });

        submitReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitReportIntent = new Intent(WorkerScreenActivity.this, SubmitReportActivity.class);
                submitReportIntent.putExtra("User", (Serializable) WorkerScreenActivity.this.user);
                WorkerScreenActivity.this.startActivity(submitReportIntent);
            }
        });

        viewReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewReportIntent = new Intent(WorkerScreenActivity.this, ViewReportActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                WorkerScreenActivity.this.startActivity(viewReportIntent);
            }
        });

        qualityReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitQualityReportIntent = new Intent(WorkerScreenActivity.this, SubmitWaterQualityReport.class);
                submitQualityReportIntent.putExtra("User", (Serializable) WorkerScreenActivity.this.user);
                WorkerScreenActivity.this.startActivity(submitQualityReportIntent);
            }
        });

        viewQualityReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewQualityReportIntent = new Intent(WorkerScreenActivity.this, ViewQualityReportsActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                WorkerScreenActivity.this.startActivity(viewQualityReportIntent);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(WorkerScreenActivity.this, MapsActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                WorkerScreenActivity.this.startActivity(mapIntent);
            }
        });

    }
}
