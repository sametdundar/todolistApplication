package sametdundar.com.sametdundartodolistapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import sametdundar.com.sametdundartodolistapp.NoteBookDetailActivity;
import sametdundar.com.sametdundartodolistapp.R;
import sametdundar.com.sametdundartodolistapp.models.Notebook;

public class NoteBookAdapter extends RecyclerView.Adapter<NoteBookAdapter.ViewHolder> {

    private ArrayList<Notebook> list;
    private Context context;
    protected DatabaseReference databaseNotebook;


    public NoteBookAdapter(Context context, ArrayList<Notebook> list, DatabaseReference databaseNotebook) {
        this.context = context;
        this.list = list;
        this.databaseNotebook = databaseNotebook;
    }

    @NonNull
    @Override
    public NoteBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notebook, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NoteBookAdapter.ViewHolder holder, int position) {

        Notebook notebook = list.get(position);

        holder.tvTitle.setText(notebook.getNoteBookName());
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference drDelete = databaseNotebook.child(notebook.getId());
                drDelete.removeValue();
            }
        });

        holder.llNotebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteBookDetailActivity.startNoteBookDetail(context, notebook);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        Button tvDelete;
        LinearLayout llNotebook;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            llNotebook = itemView.findViewById(R.id.ll_notebook);
        }
    }
}
