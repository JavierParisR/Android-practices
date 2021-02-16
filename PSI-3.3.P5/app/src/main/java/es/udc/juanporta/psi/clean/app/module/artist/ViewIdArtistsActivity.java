package es.udc.juanporta.psi.clean.app.module.artist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import es.udc.juanporta.psi.clean.R;

public class ViewIdArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_id_artists);
    }

    public void estadoCheckbox(View view){

        CheckBox checkBox = findViewById(R.id.checkBox);
        Boolean isChecked = checkBox.isChecked();

        String preferencesFile = "options_preferences";
        SharedPreferences sharedPreferences = this.getSharedPreferences(preferencesFile,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("viewId", isChecked);
        editor.apply();
    }
}