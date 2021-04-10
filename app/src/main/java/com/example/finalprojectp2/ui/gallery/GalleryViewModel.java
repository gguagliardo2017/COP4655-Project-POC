package com.example.finalprojectp2.ui.gallery;

import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectp2.R;

import org.json.JSONException;
import org.json.JSONObject;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    String test;
    TextView testView;


    public GalleryViewModel() {


        mText = new MutableLiveData<>();
        mText.setValue("OK");
    }

    public LiveData<String> getText() {
        return mText;
    }
}