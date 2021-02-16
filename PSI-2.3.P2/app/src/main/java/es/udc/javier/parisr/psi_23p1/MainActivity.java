package es.udc.javier.parisr.psi_23p1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TopFragment.OnButtonSelectedListener {

    TopFragment topFragment = new TopFragment();
    BottomFragment bottomFragment = new BottomFragment();
    WebViewFragment webViewFragment = new WebViewFragment();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_topFragment,topFragment);
        fragmentTransaction.add(R.id.fl_bottomFragment, bottomFragment);
        fragmentTransaction.commit();
    }

    public void onButtonSelected(String text){

        if (text.equals("Botón C")){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_bottomFragment,webViewFragment);
            fragmentTransaction.commit();
        }
        else{
            //Create a new fragment for the textView
            BottomFragment fragment = new BottomFragment();
            bundle.putString("string",text);
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_bottomFragment, fragment);
            fragmentTransaction.commit();
        }
    }
}