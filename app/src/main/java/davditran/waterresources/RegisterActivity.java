package davditran.waterresources;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

import davditran.waterresources.model.SerializationController;
import davditran.waterresources.model.User;

public class RegisterActivity extends AppCompatActivity {

    //UI References
    private EditText mPasswordView;
    private EditText mUsernameView;
    private EditText mEmailView;
    private Button registerButton;
    private Spinner mAccountType;
    private Button cancelButton;
    private ProgressDialog progressDialog;
    private SerializationController serializationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        firebaseAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
        serializationController = SerializationController.getInstance();


        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailView = (EditText) findViewById(R.id.email);
        mUsernameView = (EditText) findViewById(R.id.username);
        registerButton = (Button) findViewById(R.id.registerButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Nice");
                registerUserNoDB();
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog = new ProgressDialog(this);

        //creates spinner for account type
        mAccountType = (Spinner) findViewById(R.id.accountType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.account_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountType.setAdapter(adapter);
    }

    private void registerUserNoDB() {
        User user = new User(mUsernameView.getText().toString(), mPasswordView.getText().toString(),
                mAccountType.getItemAtPosition(mAccountType.getSelectedItemPosition()).toString(), mEmailView.getText().toString());
        if (isInputValid()) {
            serializationController.retrieveChanges(this, "users");
            ArrayList<User> userList = SerializationController.users;
            userList.add(user);
            serializationController.saveChanges(this, "users", SerializationController.users);
            Log.d("Register", "Register success");
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        ArrayList<User> userList = serializationController.users;
        HashMap<String, User> registeredUserMap = new HashMap<>();
        for (User user : userList) {
            if (user != null) {
                registeredUserMap.put(user.getUsername(), user);
            }
        }
        if (registeredUserMap.containsKey(mUsernameView.getText())) {
            errorMessage += "Username already exists!\n";
        }
        if (mPasswordView.getText() == null || mPasswordView.getText().length() < 4) {
            errorMessage += "No valid password entered! Please make password longer than four characters! \n";
        }
        if (mEmailView.getText() == null || mEmailView.getText().length() == 0 || !mEmailView.getText().toString().contains("@")) {
            errorMessage += "No valid email entered!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

//    private void registerUser() {
//        final String email = mEmailView.getText().toString().trim();
//        final String password = mPasswordView.getText().toString().trim();
//        final String username = mUsernameView.getText().toString().trim();
//
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "Please Enter an Email", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(this, "Please Enter a Username", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressDialog.setMessage("Registering User...");
//        progressDialog.show();
//
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.hide();
//                        if (task.isSuccessful()) {
//                            System.out.println("Success!");
//                            onAuthSuccess(task.getResult().getUser());
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "Could not register, please try again", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//    private void onAuthSuccess(FirebaseUser user) {
//        final String password = mPasswordView.getText().toString().trim();
//        final String username = mUsernameView.getText().toString().trim();
//        String accountType;
//        if (mAccountType.getSelectedItem() == null) {
//            accountType = "User";
//        } else {
//            accountType = mAccountType.getItemAtPosition(mAccountType.getSelectedItemPosition()).toString();
//        }
//        // Write new user
//        writeNewUser(user.getEmail(), user.getUid(), username, password, accountType);
//
//        // Go to MainActivity
//        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//        finish();
//    }
//
//    private void writeNewUser(String email, String userID, String username, String password, String accountType) {
//        User user =  new User(username, password, accountType, email);
//        mDatabase.child("users").child(userID).setValue(user);
//    }
}
