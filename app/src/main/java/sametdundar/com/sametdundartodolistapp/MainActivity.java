package sametdundar.com.sametdundartodolistapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import sametdundar.com.sametdundartodolistapp.adapters.NoteBookAdapter;
import sametdundar.com.sametdundartodolistapp.models.Note;
import sametdundar.com.sametdundartodolistapp.models.Notebook;
import sametdundar.com.sametdundartodolistapp.models.User;

public class MainActivity extends BaseActivity {

    @BindView(R.id.et_add)
    EditText etAdd;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    FirebaseUser user;
    ArrayList<Notebook> notebookArrayList= new ArrayList<>();
    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getActivityName() {
        return "MainActivity";
    }

    @Override
    public String getScreenName() {
        return "Ana Ekran";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseNotebook = FirebaseDatabase.getInstance().getReference("notebook");
        user = mAuth.getCurrentUser();

        NoteBookAdapter adapter = new NoteBookAdapter(MainActivity.this,notebookArrayList,databaseNotebook);
        recyclerView.setAdapter(adapter);

        databaseNotebook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                notebookArrayList.clear();
                for(DataSnapshot notebookSnapshot : dataSnapshot.getChildren()){

                    Log.e("notebook",notebookSnapshot.toString());

                    Notebook notebook = notebookSnapshot.getValue(Notebook.class);
                    Log.e("notebook",String.valueOf(notebook.getNoteBookName()));

                    if (notebook.getUserId().equals(user.getUid())) {
                        notebookArrayList.add(notebook);
                    }

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.btn_profile) public void btnProfile(){
        ProfileAcitivty.startProfileActivity(this);
    }

    @OnClick(R.id.btn_add) public void btnAdd(){

        String value = etAdd.getText().toString();
        String id = databaseNotebook.push().getKey();

        Notebook notebook = new Notebook();
        notebook.setNoteBookName(value);
        notebook.setUserId(user.getUid());
        notebook.setId(id);

        databaseNotebook.child(id).setValue(notebook);


    }
}
