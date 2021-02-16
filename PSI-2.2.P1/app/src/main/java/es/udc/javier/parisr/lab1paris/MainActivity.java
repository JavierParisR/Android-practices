package es.udc.javier.parisr.lab1paris;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "es.udc.javier.parisr.MESSAGE";
    public static final String RETURNED_MESSAGE = "es.udc.javier.parisr.RETURNED_MESSAGE";
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {

                String requiredValue = data.getStringExtra(RETURNED_MESSAGE);
                Toast.makeText(MainActivity.this, requiredValue, Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    //Method for the bt__main_continue
    public void sendMessage(View view) {
        final EditText editText = findViewById(R.id.et_main);
        String message = editText.getText().toString();
        if (message.equals("")){
            Toast.makeText(MainActivity.this, "Es necesario introducir texto", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    //Method for the bt_main_share
    public void shareMessage(View view){
        final EditText editText = findViewById(R.id.et_main);
        String message = editText.getText().toString();
        if (message.equals("")){
            Toast.makeText(MainActivity.this, "Es necesario introducir texto", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(intent, "");
            startActivity(shareIntent);
        }
    }
}