package sametdundar.com.sametdundartodolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import sametdundar.com.sametdundartodolistapp.adapters.NoteAdapter;
import sametdundar.com.sametdundartodolistapp.adapters.NoteBookAdapter;
import sametdundar.com.sametdundartodolistapp.models.Note;
import sametdundar.com.sametdundartodolistapp.models.Notebook;

import static android.os.Build.ID;

public class NoteBookDetailActivity extends BaseActivity {


    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static String NOTEBOOK ="notebook";
    Notebook notebook;
    ArrayList<Note> noteArrayList = new ArrayList<Note>();

    public static void startNoteBookDetail(Context context, Notebook notebook) {
        Intent intent = new Intent(context, NoteBookDetailActivity.class);
        intent.putExtra(NOTEBOOK,notebook);
        context.startActivity(intent);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_note_book_detail;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getActivityName() {
        return "NoteBookDetailActivity";
    }

    @Override
    public String getScreenName() {
        return "Notebook Detay";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            notebook = (Notebook) getIntent().getExtras().getSerializable(NOTEBOOK);
        }
        databaseNote = FirebaseDatabase.getInstance().getReference("note");



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter noteAdapter = new NoteAdapter(NoteBookDetailActivity.this,noteArrayList,databaseNote);
        recyclerView.setAdapter(noteAdapter);


        Log.e(NOTEBOOK,notebook.getNoteBookName());
        tvTitle.setText(notebook.getNoteBookName());

        databaseNote.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                noteArrayList.clear();
                for(DataSnapshot noteSnapshot : dataSnapshot.getChildren()){

                    Log.e("note",noteSnapshot.toString());

                    Note note = noteSnapshot.getValue(Note.class);
                    Log.e("note",String.valueOf(note.getDescription()));

                    if (note.getNotebookId().equals(notebook.getId())) {
                        noteArrayList.add(note);
                    }

                }

                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.btn_delete)
    public void btnDelete(){

        DatabaseReference drDelete = FirebaseDatabase.getInstance().getReference("notebook").child(notebook.getId());
        drDelete.removeValue();
        finish();

    }
    @OnClick(R.id.btn_add)
    public void btnAdd(){

        AddNoteActivity.startAddNoteActivity(this,notebook.getId());

    }

    @OnClick(R.id.btn_email)public void btnEmail(){
        String data= notebook.getNoteBookName() +"\n";
        for(Note note: noteArrayList){
            data = data +" \n "+ note.getName() + " : " + note.getDescription() ;
        }

        sendEmail(data);
    }

    protected void sendEmail(String data) {
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ToDoList");
        emailIntent.putExtra(Intent.EXTRA_TEXT, data);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sendin", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(NoteBookDetailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
