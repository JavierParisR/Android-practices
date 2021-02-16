package es.udc.javier.parisr.psi_24p3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.tv_title_detail);
        TextView subtitle = findViewById(R.id.tv_subtitle_detail);
        TextView description = findViewById(R.id.tv_description_detail);

        //Recupero el intent y el bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        //Obtengo el item y lo envio a la vista.
        try{
            Item item = (Item) bundle.getSerializable("item");
            title.setText(item.getTitle());
            subtitle.setText(item.getSubtitle());
            description.setText(item.getDescription());
        }catch (NullPointerException e){
            Toast.makeText(this, "No existen items", Toast.LENGTH_LONG).show();
        }
    }
}