package sametdundar.com.sametdundartodolistapp;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileAcitivty extends BaseActivity {


    @BindView(R.id.tv_mail)
    TextView tvMail;

    public static void startProfileActivity(Context context) {
        Intent intent = new Intent(context, ProfileAcitivty.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_profile_acitivty;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getActivityName() {
        return "ProfileActivity";
    }

    @Override
    public String getScreenName() {
        return getString(R.string.profile_toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvMail.setText(mAuth.getCurrentUser().getEmail());

    }

    @OnClick(R.id.btn_logout)
    public void btnLogout() {
        mAuth.signOut();
        finish();
        SignInActivity.startSignInActivity(this);
    }

    @OnClick(R.id.btn_main)
    public void btnMain() {
        finish();
        MainActivity.startMainActivity(this);
    }
}
