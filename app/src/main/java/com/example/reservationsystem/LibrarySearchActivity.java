package com.example.reservationsystem;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.L;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LibrarySearchActivity extends AppCompatActivity {

    // 검색 옵션 지정화면
    public TextView option_seat_t, option_availability_t;
    // 검색어 + view
    private EditText searchSeatet;
    private RecyclerView mRecyclerView;

    // 설정
    private LibrarySeatAdaptor librarySeatAdaptor;
    private ArrayList<LibrarySeatInfo> librarySeatInfo = new ArrayList<>();
    private ArrayList<LibrarySeatInfo> librarySeatInfoInput = new ArrayList<>();

    // 파베
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // 스피너
    private String option_seat, option_status;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraryseatsearch);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecycleSearchPostList);

        searchSeatet = findViewById(R.id.editSearch);
        option_availability_t = findViewById(R.id.option_seat_availability);
        option_seat_t = findViewById(R.id.option_seat_location);

        // 좌석위치 스피너 선택시 이벤트
        Spinner seatLocationSpinner = (Spinner)findViewById(R.id.spinner_seat_location);
        seatLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                option_seat_t.setText(adapterView.getItemAtPosition(position).toString());
                option_seat = adapterView.getItemAtPosition(position).toString();

                settingLibrarySeatList(option_seat);
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

        searchSeatet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            // input창에 문자를 입력할때마다 호출된다.
            @Override
            public void afterTextChanged(Editable editable) {
                String text = searchSeatet.getText().toString();
                search_librarySeat(text);
            }
        });
    }


    public void search_librarySeat(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        librarySeatInfoInput.clear();
        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            settingLibrarySeatList(option_seat);
        }
        // 문자 입력 발생
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < librarySeatInfo.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (librarySeatInfo.get(i).getNumber().toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    librarySeatInfoInput.add(librarySeatInfo.get(i));
                    }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        librarySeatAdaptor = new LibrarySeatAdaptor(getApplicationContext(), librarySeatInfoInput);
        mRecyclerView.setAdapter(librarySeatAdaptor);
        librarySeatAdaptor.notifyDataSetChanged();

    }

    private void settingLibrarySeatList(String location){
        db.collection(location)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            librarySeatInfo = new ArrayList<>();

                            for(QueryDocumentSnapshot document : task.getResult()) {
                                librarySeatInfo.add(new LibrarySeatInfo(
                                        document.getData().get("number").toString(),
                                        document.getData().get("status").toString(),
                                        document.getData().get("time").toString()));
                            }
                            librarySeatAdaptor = new LibrarySeatAdaptor(getApplicationContext(), librarySeatInfo);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            mRecyclerView.setAdapter(librarySeatAdaptor);
                        }else{
                            Log.e("Error", "task Error!");
                        }

                    }
                });

    }

}
