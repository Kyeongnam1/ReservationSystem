package com.example.reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;


public class ReservationDetail extends AppCompatActivity {

    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reservation_detail);

        txtText = (TextView)findViewById(R.id.txtText);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        txtText.setText(data);

    }

    public void mOnClose(View v){

        Intent intent = new Intent();
        intent.putExtra("result", "팝업 닫기");
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        return;
    }

}