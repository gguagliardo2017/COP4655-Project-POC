package com.example.finalprojectp2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectp2.Crypto;
import com.example.finalprojectp2.CryptoAdapter;
import com.example.finalprojectp2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    SearchView searchView;
    String url;
    String symbol;
    String name;
    String iconUrl;
    String price;
    String change;
    ListView listView;
    Crypto crypto;
    Button favorite;
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Favs/Favs");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);


        searchView = (SearchView) root.findViewById(R.id.newSearch);
        listView = (ListView) root.findViewById(R.id.ListView);
        favorite = (Button) root.findViewById(R.id.button);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

                if (query.equals("btc") || query.equals("BTC")) {
                    query = "Qwsogvtv82FCd";
                } else if (query.equals("eth") || query.equals("ETH")) {
                    query = "razxDUgYGNAdQ";
                } else if (query.equals("xrp") || query.equals("XRP")) {
                    query = "-l8Mn2pVlRs-p";
                } else if (query.equals("usdt") || query.equals("USDT")) {
                    query = "HIVsRcGKkPFtW";
                } else if (query.equals("ada") || query.equals("ADA")) {
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

                                    crypto = new Crypto(symbol, name, iconUrl, price, change);
                                    crypto.setSymbol(coins.getString("symbol"));
                                    crypto.setName(coins.getString("name"));
                                    crypto.setIconUrl(coins.getString("iconUrl"));
                                    crypto.setPrice(coins.getString("price"));
                                    crypto.setChange(coins.getString("change"));


                                } catch (JSONException err) {
                                    Log.d("Error", err.toString());
                                }

                                ArrayList<Crypto> listOne = new ArrayList<Crypto>();

                                listOne.add(crypto);

                                CryptoAdapter adapter = new CryptoAdapter(getActivity(), R.layout.adapter_view_layout, listOne);
                                listView.setAdapter(adapter);

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

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("map", crypto);


                mDocRef.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    private static final String TAG = "";

                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "It works");
                        Toast toast = Toast.makeText(getActivity(),"Crypto Added / Removed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    private static final String TAG = "";

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "it doesn't work");
                    }
                });
            }
        });

        return root;
    }

}


