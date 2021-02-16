package es.udc.javier.parisr.psi_23p1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class BottomFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        //Get the string from the bundle provided by MainActivity
        String text = this.getArguments().getString("string");

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(text);
        return  view;
    }
}