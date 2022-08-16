package com.example.reservationsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LibrarySeatAdaptor extends RecyclerView.Adapter<LibrarySeatAdaptor.ViewHolder> {

    private ArrayList<LibrarySeatInfo> librarySeatInfo;
    private Context mContext;

    // search랑 연결위한 어댑터
    private Context context;
    private List<LibrarySeatItem> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;

    public LibrarySeatAdaptor(Context context, ArrayList<LibrarySeatInfo> list){
        this.mContext = context;
        this.librarySeatInfo = list;
    }

    public LibrarySeatAdaptor(List<LibrarySeatAdaptor> list, LibrarySeatAdaptor searchActivity) {
    }

    public LibrarySeatAdaptor(List<LibrarySeatItem> list, Context context){
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LibrarySeatAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.libraryseat_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrarySeatAdaptor.ViewHolder holder, int position) {
        holder.seat_number.setText(librarySeatInfo.get(position).getNumber());
        holder.seat_status.setText(librarySeatInfo.get(position).getStatus());
        holder.seat_time.setText(librarySeatInfo.get(position).getTime());

    }

    public void setLibrarySeatList(ArrayList<LibrarySeatInfo> list){
        this.librarySeatInfo = list;
        notifyDataSetChanged();
    }

    // get
    public int getCount() {
        return list.size();
    }
    public Object getItem(int i) {
        return null;
    }
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return librarySeatInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView postImg;
        TextView seat_number, seat_status, seat_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postImg = (ImageView) itemView.findViewById(R.id.seat_img);
            seat_number = (TextView) itemView.findViewById(R.id.seat_number);
            seat_status = (TextView) itemView.findViewById(R.id.seat_status);
            seat_time = (TextView) itemView.findViewById(R.id.seat_time);

            // 여기다가 alertdialogue
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
//                        Intent intent = new Intent(mContext, PostDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("KEY", postInfo.get(pos).getPostTitle());
//                        intent.putExtra("postTitle",postTitle.getText().toString());
//                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }


}