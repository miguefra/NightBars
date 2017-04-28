package nightbars.nightbars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nightbars.nightbars.app.ConexionDBSignUp;
import nightbars.nightbars.helper.TaskCallback;

public class SignUpActivity extends AppCompatActivity implements TaskCallback {
    private static final String TAG = "SignupActivity";
    private String IP = "sql8.freesqldatabase.com:3306";
    private String dataBase = "/sql8170846";

    @InjectView(R.id.input_name)
    EditText nameText;
    @InjectView(R.id.input_email)
    EditText emailText;
    @InjectView(R.id.input_password)
    EditText passwordText;
    @InjectView(R.id.btn_signup)
    Button signupButton;
    @InjectView(R.id.link_login)
    TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the Login activity.
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        String fname = nameText.getText().toString();
        String lname = nameText.getText().toString();
        String username = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        new ConexionDBSignUp(SignUpActivity.this, this).execute(IP, dataBase, fname, lname, username, email, password);

        /*new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        //progressDialog.dismiss();
                    }
                }, 3000);*/
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        //finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Ooops! Login failed", Toast.LENGTH_LONG).show();

        //signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("Really?? At least 3 characters!");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Don't be a lier... Enter a valid email address!");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("Between 4 and 10 alphanumeric characters, please!");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    public void done() {
        finish();
    }
}
