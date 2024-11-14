package com.example.kompc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private OnBuyClickListener onBuyClickListener;


    public ProductAdapter(Context context, List<Product> productList, OnBuyClickListener onBuyClickListener) {
        this.context = context;
        this.productList = productList;
        this.onBuyClickListener = onBuyClickListener;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }


        TextView nameTextView = convertView.findViewById(R.id.productNameTextView);
        TextView priceTextView = convertView.findViewById(R.id.productPriceTextView);
        TextView brandTextView = convertView.findViewById(R.id.productBrandTextView);
        Button buyButton = convertView.findViewById(R.id.buyButton); // Botón de compra

        Product product = productList.get(position);


        nameTextView.setText(product.getName());
        priceTextView.setText("Precio: " + product.getPrice());
        brandTextView.setText("Marca: " + product.getBrand());


        buyButton.setOnClickListener(v -> {

            onBuyClickListener.onBuyClick(product);
        });

        return convertView;
    }


    public interface OnBuyClickListener {
        void onBuyClick(Product product);
    }
}
