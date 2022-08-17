package com.example.reservationsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassRoomInfoActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom2);

        recyclerView = (RecyclerView)findViewById(R.id.recyceler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ;
        // 상하 스크롤
        adapter = new Adapter();
        for (int i = 9; i < 18; i++) {
            String str = i + "시 ~"+ (i+1) + "시";
            adapter.setArrayData(str);
        }
        recyclerView.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button button;

        ViewHolder(Context context, View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.reservateButton1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity();

//                    String strText = textView.getText().toString();
//
//                    DocumentReference docRef = db.collection("classroom").document(strText);
//                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                            if (task.isSuccessful()) {
//                                DocumentSnapshot document = task.getResult();
//                                if (document.exists()) {
//                                    if(document.getData().get("status").toString().equals("예약됨")){
//                                        Toast.makeText(context, "예약이 차있습니다.", Toast.LENGTH_SHORT).show();
//                                    }else{
//                                        ClassRoomInfo classRoomInfo = new ClassRoomInfo("50명", "예약됨");
//                                        db.collection("classroom").document(strText).set(classRoomInfo)
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//                                                    }
//                                                })
//                                                .addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                    }
//                                                });
//                                    }
//                                }
//                            }
//                        }
//                    });
                    // Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
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
            View view = inflater.inflate(R.layout.activity_classroominfo, parent, false);

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

    private  void startActivity(){
        Intent intent=new Intent(this,ClassRoomInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }




}