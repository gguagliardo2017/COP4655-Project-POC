package com.example.finalprojectp2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectp2.HomePage;
import com.example.finalprojectp2.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    String test;
    TextView testView;
    ListView listView;
    String test1;
    TextView testView1;
    String test2;
    TextView testView2;
    String test3;
    TextView testView3;
    String test4;
    TextView testView4;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String WEATHER_API_KEY = "ADA";
        String API_KEY = "AOEVAIA7AKSQZXCT";

        String url ="https://www.alphavantage.co/query?function=" + "DIGITAL_CURRENCY_DAILY" +  "&symbol=" +  WEATHER_API_KEY + "&market=" + "USD" + "&apikey="+ API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response);
//                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject main = jsonObject.getJSONObject("Meta Data");
                            test = main.getString("2. Digital Currency Code");
//                            test1 = main.getString("3. Digital Currency Name");
                            test2 = main.getString("4. Market Code");
                            test3 = main.getString("7. Time Zone");
//                            test4 = main.getString("6. Last Refreshed");




                        } catch (JSONException err) {
                            Log.d("Error", err.toString());
                        }

                        testView.setText(test);
//                        ArrayList<String> listOne = new ArrayList<>(Arrays.asList(test, test2, test3));
//                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.fragment_home, listOne);
//                        System.out.println(listOne);
//                        listView.setAdapter(arrayAdapter);

//                        testView1.setText(test1);
//                        testView2.setText(test2);
//                        testView3.setText(test3);
//                        testView4.setText(test4);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);



        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        testView = root.findViewById(R.id.CryptoView);







        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                testView.setText(s);
            }
        });

        return root;


    }
}