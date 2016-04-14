package com.team18.teamproject.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Daniel on 14/04/2016.
 */
public class CustomStringRequest extends StringRequest {

    private Map<String,String> bodyParams;

    public CustomStringRequest(int method, String url, Map<String,String> bodyParams, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.bodyParams = bodyParams;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return bodyParams;
    }
}
