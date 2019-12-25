package com.example.itime;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountdownItemAdapter extends RecyclerView.Adapter<CountdownItemAdapter.ViewHolder>{

    private Context context;
    private List<CountdownItem> countdownItemList;

    public CountdownItemAdapter(List<CountdownItem> countdownItemList){
        this.countdownItemList = countdownItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.countdown_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountdownItem countDownItem = countdownItemList.get(position);
        holder.countdown.setText(countDownItem.getCountdown());
        holder.title.setText(countDownItem.getTitle());
        holder.time.setText(countDownItem.getTime());
        holder.remark.setText(countDownItem.getRemark());
        holder.imageView.setImageResource(countDownItem.getImageId());
        holder.linearLayout.setBackgroundResource(countDownItem.getImageId());
    }

    @Override
    public int getItemCount() {
        return countdownItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView countdown;
        TextView title;
        TextView time;
        TextView remark;
        ImageView imageView;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            countdown = cardView.findViewById(R.id.countdown_item_countdown);
            title = cardView.findViewById(R.id.countdown_item_title);
            time = cardView.findViewById(R.id.countdown_item_time);
            remark = cardView.findViewById(R.id.countdown_item_remark);
            imageView = cardView.findViewById(R.id.countdown_item_image);
            linearLayout = cardView.findViewById(R.id.countdown_item_linearlayout);
        }

    }


}
