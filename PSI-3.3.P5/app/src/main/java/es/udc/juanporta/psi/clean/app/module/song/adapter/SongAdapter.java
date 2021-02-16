package es.udc.juanporta.psi.clean.app.module.song.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.module.song.viewmodel.SongViewModel;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    private List<SongViewModel> mItems;

    public SongAdapter() {

        mItems = new ArrayList<>();
    }

    public void setItems(List<SongViewModel> items) {

        mItems = items;
        notifyDataSetChanged();
    }

    public void updateItem(SongViewModel item,
                           int position) {

        mItems.add(position, item);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public SongAdapter.SongHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song, parent, false);
        return new SongAdapter.SongHolder(v);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongHolder holder,
                                 int position) {

        holder.bind(mItems.get(position));
    }

    static class SongHolder extends RecyclerView.ViewHolder {

        private TextView mTvId;
        private TextView mTvName;

        private SongHolder(View v) {

            super(v);
            mTvId = v.findViewById(R.id.row_song_id);
            mTvName = v.findViewById(R.id.row_song_name);
        }

        private void bind(SongViewModel song) {

            mTvId.setText("-");
            String name = song.getName();

            mTvName.setText(name);
        }
    }
}