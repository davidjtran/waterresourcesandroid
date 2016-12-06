package davditran.waterresources;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

import davditran.waterresources.model.User;

public class MainScreenActivity extends AppCompatActivity {

    //UI References
    private Button submitReportsButton;
    private Button userProfileButton;
    private Button viewReportsButton;
    private Button qualityReportButton;
    private Button viewQualityReportButton;
    private Button mapButton;
    private Button graphButton;
    private Button cancelButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        this.user = (User) getIntent().getSerializableExtra("User");

        submitReportsButton = (Button) findViewById(R.id.submitReportButton);
        userProfileButton = (Button) findViewById(R.id.userProfileButton);
        viewReportsButton = (Button) findViewById(R.id.viewReportsButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        qualityReportButton = (Button) findViewById(R.id.qualityReportButton);
        viewQualityReportButton = (Button) findViewById(R.id.viewQualityReportButton);
        mapButton = (Button) findViewById(R.id.mapButton);
        graphButton = (Button) findViewById(R.id.graphButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userProfileIntent = new Intent(MainScreenActivity.this, UserProfileActivity.class);
                userProfileIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                MainScreenActivity.this.startActivity(userProfileIntent);
            }
        });

        submitReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitReportIntent = new Intent(MainScreenActivity.this, SubmitReportActivity.class);
                submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                MainScreenActivity.this.startActivity(submitReportIntent);
            }
        });

        viewReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewReportIntent = new Intent(MainScreenActivity.this, ViewReportActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                MainScreenActivity.this.startActivity(viewReportIntent);
            }
        });

        qualityReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitQualityReportIntent = new Intent(MainScreenActivity.this, SubmitWaterQualityReport.class);
                submitQualityReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                MainScreenActivity.this.startActivity(submitQualityReportIntent);
            }
        });

        viewQualityReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewQualityReportIntent = new Intent(MainScreenActivity.this, ViewQualityReportsActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                MainScreenActivity.this.startActivity(viewQualityReportIntent);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(MainScreenActivity.this, MapsActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                MainScreenActivity.this.startActivity(mapIntent);
            }
        });

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent graphIntent = new Intent(MainScreenActivity.this, GraphPromptActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                MainScreenActivity.this.startActivity(graphIntent);
            }
        });
    }
}
