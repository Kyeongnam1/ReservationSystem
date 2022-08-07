package com.example.reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText idET;
    EditText pwET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.RsignUpButton).setOnClickListener(onClickListener);
        idET = findViewById(R.id.RidEditText);
        pwET = findViewById(R.id.RpasswordEditText);
    }

    View.OnClickListener onClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.RsignUpButton:
                    //입력값 String에 저장
                    String userID = idET.getText().toString();
                    String userPassword = pwET.getText().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.indexOf("}")+1));
                                boolean success = jsonObject.getBoolean("success");
                                if(success){
                                    //회원가입 성공
                                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else{
                                    //회원가입 실패
                                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    //서버로 Volley를 이용하여 요청
                    RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
            }
        }

    };
}