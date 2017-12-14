package ru.geekbrains.clientprovider;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private NoteSource noteSource;

    public NoteAdapter(NoteSource noteSource){
        this.noteSource = noteSource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(noteSource.getNoteByPosition(position));
    }

    @Override
    public int getItemCount() {
        return noteSource.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNote;
        private TextView textTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            textNote = itemView.findViewById(R.id.textNote);
            textTitle = itemView.findViewById(R.id.textTitle);
        }

        public void bind(Note note){
            textTitle.setText(note.title);
            textNote.setText(note.description);
        }

    }
}
