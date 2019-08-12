package sametdundar.com.sametdundartodolistapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import sametdundar.com.sametdundartodolistapp.R;
import sametdundar.com.sametdundartodolistapp.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<Note> list;
    private Context context;
    protected DatabaseReference databaseNote;


    public NoteAdapter(Context context, ArrayList<Note> list, DatabaseReference databaseNote) {
        this.context = context;
        this.list = list;
        this.databaseNote = databaseNote;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {

        Note note = list.get(position);

        holder.tvTitle.setText(note.getName());
        holder.tvDescription.setText(note.getDescription());
        holder.tvDeadLine.setText(note.getDeadLine());
        holder.cbStatus.setChecked(note.isStatus());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AddNoteActivity gidecek
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference drDelete = databaseNote.child(note.getId());
                drDelete.removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox cbStatus;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvDeadLine;
        LinearLayout llItem;
        Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_decription);
            tvDeadLine = itemView.findViewById(R.id.tv_deadline);
            cbStatus = itemView.findViewById(R.id.cb_status);
            llItem = itemView.findViewById(R.id.ll_item);
            btnDelete = itemView.findViewById(R.id.btn_delete);

        }
    }
}
