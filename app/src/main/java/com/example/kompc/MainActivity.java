package com.example.kompc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button publishSaleButton, searchProductButton, viewHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        publishSaleButton = findViewById(R.id.publishSaleButton);
        searchProductButton = findViewById(R.id.searchProductButton);
        viewHistoryButton = findViewById(R.id.viewHistoryButton);


        publishSaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, PublishSaleActivity.class));
            }
        });

        searchProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SearchProductsActivity.class)); // Cambié el nombre de la actividad
            }
        });

        viewHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ViewPurchasesActivity.class));
            }
        });
    }
}
