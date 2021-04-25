package com.example.finalprojectp2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.finalprojectp2.Crypto;
import com.example.finalprojectp2.CryptoAdapter;
import com.example.finalprojectp2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    ListView listView;
    Crypto crypto;
    String symbol;
    String name;
    String iconUrl;
    String price;
    String change;

    TextView mQuoteTextView;
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Favs/Favs");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        listView = (ListView) root.findViewById(R.id.ListView);


        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){

                  Map<String, Object> map = (Map<String, Object>) documentSnapshot.getData().get("map");

                    if(map == null || map.isEmpty()) {
                        Toast toast = Toast.makeText(getActivity(), "No Favorites", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }

                    String price1 = (String) map.get("price");
                    String symbol1 = (String) map.get("symbol");
                    String  name1 = (String) map.get("name");
                    String change1 = (String) map.get("change");
                    String iconUrl1 = (String) map.get("iconUrl");


                    crypto = new Crypto(symbol, name, iconUrl, price, change);
                    crypto.setPrice(price1);
                    crypto.setSymbol(symbol1);
                    crypto.setChange(change1);
                    crypto.setName(name1);
                    crypto.setIconUrl(iconUrl1);


                   ArrayList<Crypto> listOne = new ArrayList<Crypto>();
                    listOne.add(crypto);

                  CryptoAdapter adapter = new CryptoAdapter(getActivity(), R.layout.adapter_view_layout, listOne);
                   listView.setAdapter(adapter);

                }
            }
        });

        return root;
    }
}