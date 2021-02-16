package es.udc.javier.parisr.psi_24p3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.udc.javier.parisr.psi_24p3.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Item> mData;
    private ItemClickListener mClickListener;

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title;
        TextView tv_subtitle;

        ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_subtitle = itemView.findViewById(R.id.tv_subtitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    //Constructor del adaptador
    public MyAdapter(List<Item> data) {
        this.mData = data;
    }

    //Infla el layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mData.get(position);
        holder.tv_title.setText(item.getTitle());
        holder.tv_subtitle.setText(item.getSubtitle());
    }

    //Numero total de items
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //Obtener el item de la posicion
    Item getItem(int id) {
        return mData.get(id);
    }

    //Captura los clicks en los items
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // Interfaz para la MainActivity
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
