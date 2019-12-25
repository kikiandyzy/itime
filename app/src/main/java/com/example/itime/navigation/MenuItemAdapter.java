package com.example.itime.navigation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.itime.R;

import java.util.List;
//模拟菜单的list的配适器
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private int resourceId;
    //在list中的位置
    private int location;
    //判断添加标签的箭头的状态
    private int change = 3;
    ////再次测试


    public MenuItemAdapter(Context context, int textViewResourceId, List<MenuItem> objects){

        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
        location = objects.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // Log.d("kiki","getView");
        MenuItem menuItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView icon = view.findViewById(R.id.menu_item_icon);
        TextView name = view.findViewById(R.id.menu_item_name);
        ImageView secondIcon = view.findViewById(R.id.menu_item_second_icon);
        icon.setImageResource(menuItem.getIcon());
        name.setText(menuItem.getName());
        secondIcon.setImageResource(menuItem.getSecondIcon());
        //由于菜单会动态的添加或减少，下划线通过位置来判断
        if(position == location-4){
            View separator = view.findViewById(R.id.menu_item_separator);
            separator.setVisibility(View.VISIBLE);
        }
        if((position == 1) && change != 3){

            if(change == 0){
                RotateAnimation mFlipAnimation = new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                mFlipAnimation.setInterpolator(new LinearInterpolator());
                mFlipAnimation.setDuration(250);
                mFlipAnimation.setFillAfter(true);
                //mFlipAnimation.setRepeatMode(Animation.REVERSE);
                ImageView mPull_to_refresh_image = view.findViewById(R.id.menu_item_second_icon);
                mPull_to_refresh_image.startAnimation(mFlipAnimation);

            }else if(change  == 1){
                RotateAnimation mFlipAnimation = new RotateAnimation(180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                mFlipAnimation.setInterpolator(new LinearInterpolator());
                mFlipAnimation.setDuration(250);
                mFlipAnimation.setFillAfter(true);
                //mFlipAnimation.setRepeatMode(Animation.REVERSE);
                ImageView mPull_to_refresh_image = view.findViewById(R.id.menu_item_second_icon);
                mPull_to_refresh_image.startAnimation(mFlipAnimation);

            }
        }
        if(menuItem.getState()== MenuItem.DELETABLE || menuItem.getState()== MenuItem.ADDIBLE){
            //如果是可以删除的标签，动态将左边距离拉远以显示层次感
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) icon.getLayoutParams();
            linearParams.leftMargin = 70;
            icon.setLayoutParams(linearParams);
        }


        return view;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setChange(int change) {
        this.change = change;
    }
}
