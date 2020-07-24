package com.myapplicationdev.android.p10knowyourfacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag2 extends Fragment {

    ImageView iv1;
    TextView working;


    public frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_frag2,container,false);

        iv1 = root.findViewById(R.id.myZoomageView);

        iv1.setImageResource(R.mipmap.ic_launcher);
        String imageUrl = "https://www.wtffunfact.com/wp-content/uploads/2020/07/WTF-Fun-Fact-Email-Pollution-1.png ";
        Picasso.with(this.getContext()).load(imageUrl).into(iv1);

        return root;
    }
}
