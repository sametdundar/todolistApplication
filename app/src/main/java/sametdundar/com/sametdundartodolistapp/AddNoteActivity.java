package sametdundar.com.sametdundartodolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import sametdundar.com.sametdundartodolistapp.models.Note;

public class AddNoteActivity extends BaseActivity {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.et_date)
    EditText etDeadline;
    @BindView(R.id.cb_status)
    CheckBox cbStatus;

    private static String ID = "id";
    String notebookId;
    Calendar myCalendar = Calendar.getInstance();

    public static void startAddNoteActivity(Context context, String id) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        intent.putExtra(ID, id);
        context.startActivity(intent);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_add_note;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getActivityName() {
        return "AddNoteActivity";
    }

    @Override
    public String getScreenName() {
        return "Add Note";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notebookId = getIntent().getExtras().getString(ID);
        databaseNote = FirebaseDatabase.getInstance().getReference("note");


    }

    @OnClick(R.id.btn_add)
    public void btnAdd() {

        String id = databaseNote.push().getKey();


        Note note = new Note(id, notebookId,
                etTitle.getText().toString(),
                etDescription.getText().toString(),
                etDeadline.getText().toString(),
                cbStatus.isChecked());

        databaseNote.child(id).setValue(note);

        finish();


    }

    @OnClick(R.id.et_date)
    public void getDate() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        new DatePickerDialog(AddNoteActivity.this,date,myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDeadline.setText(sdf.format(myCalendar.getTime()));
    }
}
