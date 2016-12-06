package davditran.waterresources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import davditran.waterresources.model.SerializationController;
import davditran.waterresources.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private TextView emailEdit;
    private TextView addressEdit;
    private TextView titleEdit;
    private TextView passwordEdit;
    private Button saveButton;
    private Button cancelButton;
    private User user;
    private SerializationController serializationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.user = (User) getIntent().getSerializableExtra("User");
        serializationController = SerializationController.getInstance();

        emailEdit = (TextView) findViewById(R.id.emailEdit);
        passwordEdit = (TextView) findViewById(R.id.passwordEdit);
        addressEdit = (TextView) findViewById(R.id.addressEdit);
        titleEdit = (TextView) findViewById(R.id.titleEdit);
        saveButton = (Button) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void editProfile() {
        serializationController.retrieveChanges(this, "users");
        for (User u : serializationController.users) {
            if (u.getUsername().equals(this.user.getUsername())) {
                if (emailEdit.getText() != null && emailEdit.getText().length() > 0 && emailEdit.getText().toString().contains("@") ) {
                    u.setEmail(emailEdit.getText().toString());
                }
                if (passwordEdit.getText() != null && passwordEdit.getText().length() >= 4) {
                    u.setPassword(passwordEdit.getText().toString());
                }
                if (addressEdit.getText() != null && addressEdit.getText().length() > 0) {
                    u.setAddress(addressEdit.getText().toString());
                }
                if (titleEdit.getText() != null && titleEdit.getText().length() > 0) {
                    u.setTitle(titleEdit.getText().toString());
                }
            }
        }
        serializationController.saveChanges(this, "users", serializationController.users);
    }
}
