package com.example.reservationsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassRoomActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        recyclerView = (RecyclerView)findViewById(R.id.recyceler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ;
        // 상하 스크롤

        adapter = new Adapter();
        for (int i = 0; i < 100; i++) {
            String str = i + "번 강의실";
            adapter.setArrayData(str);
        }

        recyclerView.setAdapter(adapter);

    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button button;

        ViewHolder(Context context, View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strText = textView.getText().toString();
                    Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private ArrayList<String> arrayList;

        public Adapter() {
            arrayList = new ArrayList<>();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_classroom_list, parent, false);

            ViewHolder viewholder = new ViewHolder(context, view);

            return viewholder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String text = arrayList.get(position);
            holder.textView.setText(text);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        // 데이터를 입력
        public void setArrayData(String strData) {
            arrayList.add(strData);
        }

    }




}