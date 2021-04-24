package com.example.finalprojectp2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource;
import com.example.finalprojectp2.R;
import com.example.finalprojectp2.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    //    private HomeViewModel homeViewModel;
    String test;
    ListView listView;
    SearchView searchView;
    String url;

    ImageView CryptoImage;
    TextView cryptoname;
    TextView symbol;
    TextView price;
    TextView change;
    String symboldata;
    String symboldata1;
    String symboldata2;
    String symboldata3;
    String symboldata4;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = (SearchView) root.findViewById(R.id.newSearch);
        listView = (ListView) root.findViewById(R.id.CryptoList);


        CryptoImage=(ImageView) root.findViewById(R.id.CryptoImage);
        cryptoname =(TextView) root.findViewById(R.id.cryptoName);
        symbol =(TextView) root.findViewById(R.id.symbol);
        price =(TextView) root.findViewById(R.id.price);
        change =(TextView) root.findViewById(R.id.change);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {



            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
//                String API_KEY = "coinrankingd062b50d5908d51e3b77afa50c2eb25e0498ae710a418e6d";


                if(query.equals("btc") || query.equals("BTC")){
                    query = "Qwsogvtv82FCd";
                }else if(query.equals("eth") || query.equals("ETH")){
                    query = "razxDUgYGNAdQ";
                }else if(query.equals("xrp") || query.equals("XRP")){
                    query = "-l8Mn2pVlRs-p";
                }else if(query.equals("usdt") || query.equals("USDT")){
                    query = "HIVsRcGKkPFtW";
                }else if(query.equals("ada") || query.equals("ADA")){
                    query = "qzawljRxB5bYu";
                }


                url = "https://api.coinranking.com/v2/coin/" + query;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    JSONObject coins = data.getJSONObject("coin");
                                    symboldata  = coins.getString("symbol");
                                    symboldata1 = coins.getString("name");
                                    symboldata2 = coins.getString("iconUrl");
                                    symboldata3 = coins.getString("price");
                                    symboldata4 = coins.getString("change");

                                } catch (JSONException err) {
                                    Log.d("Error", err.toString());
                                }
//                                cryptoname.setText(test2);
                                symbol.setText(symboldata);
                                cryptoname.setText(symboldata1);
                                Utils.fetchSvg(getActivity(), symboldata2, CryptoImage);
                                price.setText(symboldata3);
                                change.setText(symboldata4 + "%");

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