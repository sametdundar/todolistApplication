package sametdundar.com.sametdundartodolistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;


public class SignInActivity extends BaseActivity {

    @BindView(R.id.button_signup)
    Button btnSignUp;
    @BindView(R.id.edittext_email)
    EditText etMail;
    @BindView(R.id.edittext_sifre)
    EditText etPassword;

    String email;
    String password;

    public static void startSignInActivity(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_sign_in;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getActivityName() {
        return "SignInActivity";
    }

    @Override
    public String getScreenName() {
        return getString(R.string.signIn_toolbar);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            if (firebaseUser.getEmail() != null) {
                MainActivity.startMainActivity(this);
                finish();
            }
        }
    }

    @OnClick(R.id.button_signup)
    public void btnSignUp() {
        SignUpActivity.startSignUpActivity(this);
    }

    @OnClick(R.id.button_login)
    public void btnLogin() {

        email = String.valueOf(etMail.getText());
        password = String.valueOf(etPassword.getText());

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            MainActivity.startMainActivity(SignInActivity.this);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
