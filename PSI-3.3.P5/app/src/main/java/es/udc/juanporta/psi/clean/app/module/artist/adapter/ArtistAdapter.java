package es.udc.juanporta.psi.clean.app.module.artist.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.module.artist.ViewIdArtistsActivity;
import es.udc.juanporta.psi.clean.app.module.artist.viewmodel.ArtistViewModel;

public class ArtistAdapter extends Adapter<ArtistAdapter.ArtistHolder> {

    private List<ArtistViewModel> mItems;

    public ArtistAdapter() {

        mItems = new ArrayList<>();
    }

    public void setItems(List<ArtistViewModel> items) {

        mItems = items;
        notifyDataSetChanged();
    }

    public void updateItem(ArtistViewModel item,
                           int position) {

        mItems.add(position, item);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public ArtistAdapter.ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_artist, parent, false);
        return new ArtistHolder(v);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistHolder holder,
                                 int position) {

        holder.bind(mItems.get(position));
    }

    static class ArtistHolder extends RecyclerView.ViewHolder {

        private TextView mTvId;
        private TextView mTvName;

        private ArtistHolder(View v) {

            super(v);
            mTvId = v.findViewById(R.id.row_artist_id);

            String preferencesFile = "options_preferences";
            SharedPreferences sharedPreferences =v.getContext().getSharedPreferences(preferencesFile,
                    Context.MODE_PRIVATE);

            Boolean show_artist_id = sharedPreferences.getBoolean("viewId", true);
            //Toast.makeText(v.getContext(), String.valueOf(show_artist_id), Toast.LENGTH_SHORT).show();

            if (show_artist_id)
                mTvId.setVisibility(View.VISIBLE);
            else
                mTvId.setVisibility(View.GONE);


            mTvName = v.findViewById(R.id.row_artist_name);
        }

        private void bind(ArtistViewModel artist) {

            mTvId.setText(artist.getId());

            String name = artist.getName();


            if (artist.getEventDate() != null) {

                name = name + " (" + artist.getEventDate() + ")";
            }

            mTvName.setText(name);
        }
    }

}
