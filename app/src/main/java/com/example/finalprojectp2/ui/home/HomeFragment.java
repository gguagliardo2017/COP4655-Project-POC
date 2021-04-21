package com.example.finalprojectp2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectp2.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

//    private HomeViewModel homeViewModel;
    String test;
    ListView listView;
    SearchView searchView;
    String test1;
    String test2;
    String test3;
    String test4;
    String test5;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = (SearchView) root.findViewById(R.id.newSearch);
        listView = (ListView) root.findViewById(R.id.CryptoList);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String API_KEY = "0sxt6lx8utiuutizbbbtawayfdi5wkc5xjojdh6k";

                String url ="https://www.alphavantage.co/query?function=" + "DIGITAL_CURRENCY_DAILY" +  "&symbol=" + query + "&market=" + "USD" + "&apikey="+ API_KEY;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response);
//                        System.out.println(response);
                                try { JSONObject jsonObject = new JSONObject(response);
                                    JSONObject main = jsonObject.getJSONObject("Meta Data");
                                    test = main.getString("2. Digital Currency Code");
                                    test1 = main.getString("3. Digital Currency Name");
                                    test2 = main.getString("4. Market Code");
                                    test3 = main.getString("7. Time Zone");
                                    test4 = main.getString("6. Last Refreshed");
                                    test5 = main.getString("1. Information");



                                } catch (JSONException err) {
                                    Log.d("Error", err.toString());
                                }
                                ArrayList<String> listOne = new ArrayList<String>(Arrays.asList(test5 + "\n"
                                        + test + "\n"
                                        + test1 + "\n"
                                        + test2 + "\n"
                                        + test3 + "\n"
                                        + test4));
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOne);
                                listView.setAdapter(arrayAdapter);

                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      }
        );



        return root;


    }
}