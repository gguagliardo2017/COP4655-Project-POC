package com.example.finalprojectp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CryptoAdapter extends ArrayAdapter<Crypto> {

    private  static final String TAG = "CryptoAdapter";
    private Context mContext;
    int mResource;
    ArrayList mObjects;

    public CryptoAdapter(Context context, int resource, ArrayList<Crypto> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mObjects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String symbol = getItem(position).getSymbol();
        String name = getItem(position).getName();
        String iconUrl = getItem(position).getIconUrl();
        String price = getItem(position).getPrice();
        String change = getItem(position).getChange();

        Crypto crypto = new Crypto(symbol, name, iconUrl, price, change);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView CryptoImageView = (ImageView) convertView.findViewById(R.id.CryptoImage);
        TextView cryptonameView = (TextView) convertView.findViewById(R.id.cryptoName);
        TextView symbolView = (TextView) convertView.findViewById(R.id.symbol);
        TextView priceView = (TextView) convertView.findViewById(R.id.price);
        TextView changeView = (TextView) convertView.findViewById(R.id.change);

        Utils.fetchSvg(getContext(), iconUrl, CryptoImageView);
        cryptonameView.setText(name);
        symbolView.setText(symbol);
        priceView.setText(price);
        changeView.setText(change + "%");



        return convertView;


    }

}
