package sametdundar.com.sametdundartodolistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import sametdundar.com.sametdundartodolistapp.models.User;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_passwordAgain)
    EditText etPasswordAgain;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_surname)
    EditText etSurname;

    public static void startSignUpActivity(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_sign_up;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getActivityName() {
        return "SignUpActivity";
    }

    @Override
    public String getScreenName() {
        return getString(R.string.signUp_toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseUser = FirebaseDatabase.getInstance().getReference("user");

    }

    @OnClick(R.id.btn_signUp)
    public void signUp() {

        String email = etMail.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String password = etPassword.getText().toString();
        String passwordAgain = etPasswordAgain.getText().toString();

        if (email.isEmpty()) {
            Utils.showWarningDialog(this, "Lütfen mail alanını boş bırakmayınız.");
        } else if (password.isEmpty()) {
            Utils.showWarningDialog(this, "Lütfen şifre alanını boş bırakmayınız.");
        } else if (name.isEmpty()) {
            Utils.showWarningDialog(this, "Lütfen isim alanını boş bırakmayınız.");
        } else if (surname.isEmpty()) {
            Utils.showWarningDialog(this, "Lütfen soyisim alanını boş bırakmayınız");
        } else if (password.length() < 6) {
            Utils.showWarningDialog(this, "Şifreniz en az 6 karakterli olmalıdır.");
        } else if (!password.equals(passwordAgain)) {
            Utils.showWarningDialog(this, "Şifreler uyuşmuyor.");
        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                MainActivity.startMainActivity(SignUpActivity.this);
                                User objectUser = new User(user.getUid(),user.getEmail(),name,surname);
                                databaseUser.child(user.getUid()).setValue(objectUser);
                                Toast.makeText(SignUpActivity.this, "Database Saved.", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                // update ui null
                            }

                            // ...
                        }
                    });
        }


    }
}
