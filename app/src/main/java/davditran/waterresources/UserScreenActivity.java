package davditran.waterresources;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

import davditran.waterresources.model.User;

public class UserScreenActivity extends AppCompatActivity {

    //UI References
    private Button submitReportsButton;
    private Button userProfileButton;
    private Button viewReportsButton;
    private Button mapButton;
    private Button cancelButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        this.user = (User) getIntent().getSerializableExtra("User");

        submitReportsButton = (Button) findViewById(R.id.submitReportButton);
        userProfileButton = (Button) findViewById(R.id.userProfileButton);
        viewReportsButton = (Button) findViewById(R.id.viewReportsButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
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
                Intent userProfileIntent = new Intent(UserScreenActivity.this, UserProfileActivity.class);
                userProfileIntent.putExtra("User", (Serializable) UserScreenActivity.this.user);
                UserScreenActivity.this.startActivity(userProfileIntent);
            }
        });

        submitReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitReportIntent = new Intent(UserScreenActivity.this, SubmitReportActivity.class);
                submitReportIntent.putExtra("User", (Serializable) UserScreenActivity.this.user);
                UserScreenActivity.this.startActivity(submitReportIntent);
            }
        });

        viewReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewReportIntent = new Intent(UserScreenActivity.this, ViewReportActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                UserScreenActivity.this.startActivity(viewReportIntent);
            }
        });


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(UserScreenActivity.this, MapsActivity.class);
                //submitReportIntent.putExtra("User", (Serializable) MainScreenActivity.this.user);
                UserScreenActivity.this.startActivity(mapIntent);
            }
        });
    }
}
