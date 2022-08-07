package com.example.reservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.classroomButton).setOnClickListener(onClickListener);
        findViewById(R.id.libraryButtion).setOnClickListener(onClickListener);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");

    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    View.OnClickListener onClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.classroomButton:

                    break;

                case R.id.libraryButtion:
                    Intent intentlib = new Intent(MainActivity.this,LibraryIntroActivity.class);
                    startActivity(intentlib);
                    break;
            }
        }
    };


}