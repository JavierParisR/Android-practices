package es.udc.javier.parisr.lab1paris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static es.udc.javier.parisr.lab1paris.MainActivity.RETURNED_MESSAGE;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textview = findViewById(R.id.textView);
        textview.setText(message);
    }

    public void returnMain(View view){

        Intent intent = getIntent();
        Button androidButton = findViewById(view.getId());
        String buttonText = androidButton.getText().toString();
        intent.putExtra(RETURNED_MESSAGE, buttonText);
        setResult(RESULT_OK, intent);
        this.finish();
    }
}