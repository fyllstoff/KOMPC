package com.example.kompc;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PurchaseAdapter extends BaseAdapter {

    private Context context;
    private List<Purchase> purchaseList;

    public PurchaseAdapter(Context context, List<Purchase> purchaseList) {
        this.context = context;
        this.purchaseList = purchaseList;
    }

    @Override
    public int getCount() {
        return purchaseList.size();
    }

    @Override
    public Object getItem(int position) {
        return purchaseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_purchase, parent, false);
        }

        TextView productNameTextView = convertView.findViewById(R.id.purchaseProductNameTextView);
        TextView priceTextView = convertView.findViewById(R.id.purchasePriceTextView);
        TextView purchaseDateTextView = convertView.findViewById(R.id.purchaseDateTextView);

        Purchase purchase = purchaseList.get(position);
        productNameTextView.setText(purchase.getProductName());
        priceTextView.setText("Precio: " + purchase.getPrice());
        purchaseDateTextView.setText("Fecha de compra: " + purchase.getPurchaseDate());

        return convertView;
    }
}
