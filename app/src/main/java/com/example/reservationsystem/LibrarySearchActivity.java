package com.example.reservationsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class LibrarySearchActivity extends AppCompatActivity {

    // 검색 옵션 지정화면
    TextView option_seat_t, option_availability_t;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraryseatsearch);

        option_availability_t = findViewById(R.id.option_seat_availability);
        option_seat_t = findViewById(R.id.option_seat_location);

        // 좌석위치 스피너 선택시 이벤트
        Spinner seatLocationSpinner = (Spinner)findViewById(R.id.spinner_seat_location);
        seatLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                option_seat_t.setText(adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // 좌석이용여부 스피너 선택시 이벤트
        Spinner seatAvailabilitySpinner = (Spinner)findViewById(R.id.spinner_search_availability);
        seatAvailabilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                option_availability_t.setText(adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        /*
        //search_type = typeSpinner.getSelectedItem().toString();
        editSearch = (EditText) findViewById(R.id.editSearch);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecycleSearchPostList);*/
    }

}
