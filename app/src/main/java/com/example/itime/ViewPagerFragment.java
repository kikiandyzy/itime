package com.example.itime;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {





    private int id;
    private int length;
    private String title;
    private String time;
    private String countdown;
    private int imageId;

    private TextView textViewTitle;
    private TextView textViewTime;
    private TextView textViewCountdown;

    private LinearLayout linearLayout_dot;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_view_pager,container,false);
        //view.setBackgroundResource(R.drawable.kiki);


        imageView = view.findViewById(R.id.viewpager_fragment_imageview);
        imageView.setImageResource(imageId);

        textViewTitle = view.findViewById(R.id.viewpager_fragment_textview_title);
        textViewTitle.setText(title);

        textViewTime = view.findViewById(R.id.viewpager_fragment_textview_time);
        textViewTime.setText(time);

        textViewCountdown = view.findViewById(R.id.viewpager_fragment_textview_countdown);
        textViewCountdown.setText(countdown);

        linearLayout_dot = view.findViewById(R.id.viewpager_fragment_linearlayout_dot);
        changeDot();
        return view;
    }

    public void setIdAndLength(int id,int length){
        this.id = id;
        this.length = length;
    }

    private void changeDot(){
        if (length != 0 & length != 1){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            layoutParams.setMargins(4, 4, 4, 4);
            for (int i = 0; i < length; i++) {
                View view = new View(getContext());
                if(i == id){
                    view.setBackgroundResource(R.drawable.main_viewpager_chencked);
                }else {
                    view.setBackgroundResource(R.drawable.main_viewpager_unchecked);
                }

                view.setLayoutParams(layoutParams);
                linearLayout_dot.addView(view);
            }
        }
    }

    public ViewPagerFragment(int id, int length, String title, String time, String countdown,int imageId){
        this.id = id;
        this.length = length;
        this.title = title;
        this.time = time;
        this.countdown = countdown;
        this.imageId = imageId;
    }

}
