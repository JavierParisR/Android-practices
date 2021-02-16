package es.udc.javier.parisr.psi_23p1;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TopFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        final Button bt_A = (Button) view.findViewById(R.id.bt_botonA);
        final Button bt_B = (Button) view.findViewById(R.id.bt_botonB);
        final Button bt_C = (Button) view.findViewById(R.id.bt_botonC);

        bt_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected(bt_A.getText().toString());
            }
        });

        bt_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected(bt_B.getText().toString());
            }
        });

        bt_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected(bt_C.getText().toString());
            }
        });
        return view;
    }

    // Container Activity must implement this interface
    public interface OnButtonSelectedListener {
        public void onButtonSelected(String text);
    }

    OnButtonSelectedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnButtonSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }
}