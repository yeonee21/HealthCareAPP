package com.example.covidapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "https://script.google.com/a/macros/ewhain.net/s/AKfycbwGdp--OeAJS17NkXqXzdfFmu_MVWWx-toa_NMvdKPR0i2_zpOBEHfkO4xLXgPnvWfsdA/exec";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}

