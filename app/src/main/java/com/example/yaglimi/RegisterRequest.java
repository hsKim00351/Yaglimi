package com.example.yaglimi;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest { //웹 연동시 사용
    final static private String URL = "http://khs081268.ivyro.net/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPW, String userPhone, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPW", userPW);
        map.put("userPhone", userPhone + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
