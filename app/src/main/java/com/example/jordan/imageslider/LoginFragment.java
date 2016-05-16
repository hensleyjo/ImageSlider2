package com.example.jordan.imageslider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jordan on 5/13/2016.
 */
public class LoginFragment extends android.app.Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);

        Button b = (Button) view.findViewById(R.id.login_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeScreen)getActivity()).closeLogin();
            }
        });
        return view;
    }

}
