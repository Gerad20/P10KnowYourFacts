package com.myapplicationdev.android.p10knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class frag1 extends Fragment {
    Button colorRandom;
    FrameLayout fl;



    public frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_frag1, container, false);
        colorRandom = root.findViewById(R.id.btnChangeColours);
        fl = root.findViewById(R.id.screen);

       colorRandom.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Random random = new Random();
               int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
               fl.setBackgroundColor(color  );


           }
       });


        return root;
    }
}
