package android.dapoerkami.com.dapoerkami.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.dapoerkami.com.dapoerkami.HomeActivity;
import android.dapoerkami.com.dapoerkami.R;
import android.dapoerkami.com.dapoerkami.register.RegisterActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignIn;

    private FirebaseAuth mAuth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
    }

    public void onSignUp(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void SignIn() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        final Intent intent = new Intent(this, HomeActivity.class);

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        progressDialog.setMessage("Signing In...");
        progressDialog.show();

        (mAuth.signInWithEmailAndPassword(email, password))
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignIn) {
            SignIn();
        }
    }
}





