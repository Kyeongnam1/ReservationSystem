package com.example.reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment{
    private Button class_button;
    private Button library_button;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        class_button = (Button) rootView.findViewById(R.id.classroomButton);
        library_button = (Button) rootView.findViewById(R.id.libraryButton);


//        Intent intent = getIntent();
//        String userID = intent.getStringExtra("userID");
//        String userPassword = intent.getStringExtra("userPassword");
        class_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClassRoomActivity();
            }
        });

        library_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return rootView;
    }

    private void startClassRoomActivity(){
        Intent intent = new Intent(this.getContext(), ClassRoomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }




}