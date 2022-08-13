package com.example.reservationsystem;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ClassRoomRequest extends StringRequest {

    //서버 URL 설정 - php파일 연동
    final static private String URL = "http://nam4338.dothome.co.kr/Login.php";
    private Map<String, String> map;

    public ClassRoomRequest(String Cid, String Capacity, String Status, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("Cid",Cid);
        map.put("Capacity",Capacity);
        map.put("Status", Status);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
