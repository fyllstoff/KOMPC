package com.example.kompc;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PublishSaleActivity extends AppCompatActivity {

    private EditText nameEditText, priceEditText, brandEditText, detailsEditText;
    private Button publishButton;
    private DatabaseReference productsDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_sale);

        nameEditText = findViewById(R.id.nameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        brandEditText = findViewById(R.id.brandEditText);
        detailsEditText = findViewById(R.id.detailsEditText);
        publishButton = findViewById(R.id.publishButton);

        productsDatabaseReference = FirebaseDatabase.getInstance().getReference("productos");

        publishButton.setOnClickListener(view -> publishProduct());
    }

    private void publishProduct() {
        String name = nameEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String brand = brandEditText.getText().toString().trim();
        String details = detailsEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(brand) || TextUtils.isEmpty(details)) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }


        String productId = productsDatabaseReference.push().getKey();

        if (productId != null) {

            Product product = new Product(productId, name, price, brand, details, false);


            productsDatabaseReference.child(productId).setValue(product)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(PublishSaleActivity.this, "Producto publicado con éxito", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PublishSaleActivity.this, "Error al publicar el producto", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
