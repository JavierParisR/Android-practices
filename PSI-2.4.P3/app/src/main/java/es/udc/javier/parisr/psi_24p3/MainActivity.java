package es.udc.javier.parisr.psi_24p3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {

    Bundle bundle = new Bundle();
    private MyAdapter adapter;
    Item item = new Item("title","subtitle","This is the description 1");
    Item item2 = new Item("title2","subtitle2","This is the description 2");
    Item item3 = new Item("title3","subtitle3","This is the description 3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Datos para el recycler view
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);
        itemList.add(item3);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_main);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        //Divider para el recycler view
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //Use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Creo el adapter de items y se paso al recyclerView
        adapter = new MyAdapter(itemList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        //Guardo en bundle el item pulsado
        bundle.putSerializable("item",adapter.getItem(position));

        //Creo un intent y le añado el bundle
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }
}