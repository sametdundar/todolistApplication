package sametdundar.com.sametdundartodolistapp;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected FirebaseAuth mAuth;
    protected DatabaseReference databaseUser;
    protected DatabaseReference databaseNotebook;
    protected DatabaseReference databaseNote;
    String TAG = "";

    protected abstract int getContentId();

    public abstract Context getContext();

    public abstract String getActivityName();

    public abstract String getScreenName();

    public void showDialog() {
    }

    public void dismissDialog() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle(getScreenName());
        TAG = getActivityName();
    }
}
