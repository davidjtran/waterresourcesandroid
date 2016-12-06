package davditran.waterresources;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import davditran.waterresources.model.User;

public class UserProfileActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView emailText;
    private TextView addressText;
    private TextView titleText;
    private TextView accountTypeText;
    private Button editProfileButton;
    private Button backButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        this.user = (User) getIntent().getSerializableExtra("User");

        nameText = (TextView) findViewById(R.id.nameText);
        emailText = (TextView) findViewById(R.id.emailText);
        addressText = (TextView) findViewById(R.id.addressText);
        titleText = (TextView) findViewById(R.id.titleText);
        accountTypeText = (TextView) findViewById(R.id.accountTypeText);
        editProfileButton = (Button) findViewById(R.id.editProfileButton);
        backButton = (Button) findViewById(R.id.backButton);

        nameText.setText(user.getUsername());
        emailText.setText(user.getEmail());
        addressText.setText(user.getAddress());
        titleText.setText(user.getTitle());
        accountTypeText.setText(user.getAccountType());

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userProfileIntent = new Intent(UserProfileActivity.this, EditProfileActivity.class);
                userProfileIntent.putExtra("User", (Serializable) UserProfileActivity.this.user);
                UserProfileActivity.this.startActivity(userProfileIntent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        nameText.setText(user.getUsername());
        emailText.setText(user.getEmail());
        addressText.setText(user.getAddress());
        titleText.setText(user.getTitle());
        accountTypeText.setText(user.getAccountType());
    }

}
