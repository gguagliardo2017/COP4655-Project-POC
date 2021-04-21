package com.example.finalprojectp2.ui.gallery;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectp2.MainActivity;
import com.example.finalprojectp2.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class GalleryFragment extends Fragment {

    SearchView searchView;
    View viewNews;
    String news;
    String newsUrl;
    String newsTitle;
    ListView listView;
    Bitmap image;
    ImageView imageView;
    String newsText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        searchView = (SearchView) root.findViewById(R.id.newSearch);
        listView = (ListView) root.findViewById(R.id.NewsList);
        imageView = (ImageView) root.findViewById(R.id.imageView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String items = "50";
                String API_KEY = "0sxt6lx8utiuutizbbbtawayfdi5wkc5xjojdh6k";

                String url = "https://cryptonews-api.com/api/v1?tickers=" + query + "&items=" + items + "&token=" + API_KEY;

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
                            JSONArray openAPI = jsonObject.getJSONArray("data");
                            JSONObject openArray = openAPI.getJSONObject(2);
                            news = openArray.getString("news_url");
                            newsUrl = openArray.getString("image_url");
                            newsTitle = openArray.getString("title");
                            newsText = openArray.getString("text");

                            Picasso.get().load(newsUrl).into(imageView);


                                } catch (JSONException err) {
                                    Log.d("Error", err.toString());
                                }
                                ArrayList<String> listOne = new ArrayList<String>(Arrays.asList(newsTitle +"\n\n" + newsText+ "\n\n" + news));
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