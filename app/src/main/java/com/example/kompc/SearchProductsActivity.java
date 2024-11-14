package com.example.kompc;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SearchProductsActivity extends AppCompatActivity implements ProductAdapter.OnBuyClickListener {

    private EditText searchEditText;
    private Button searchButton;
    private ListView productsListView;
    private ProductAdapter productAdapter;
    private ArrayList<Product> productsList;
    private DatabaseReference productsDatabaseReference;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        productsListView = findViewById(R.id.productsListView);
        productsList = new ArrayList<>();

        productsDatabaseReference = FirebaseDatabase.getInstance().getReference("productos");
        mAuth = FirebaseAuth.getInstance();

        productAdapter = new ProductAdapter(this, productsList, this);
        productsListView.setAdapter(productAdapter);

        searchButton.setOnClickListener(view -> searchProducts());
    }

    private void searchProducts() {
        String query = searchEditText.getText().toString().trim();

        if (TextUtils.isEmpty(query)) {
            Toast.makeText(this, "Por favor ingrese un término de búsqueda", Toast.LENGTH_SHORT).show();
            return;
        }


        productsDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);


                    if (product != null && product.getName().toLowerCase().contains(query.toLowerCase())) {
                        productsList.add(product);
                    }
                }


                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SearchProductsActivity.this, "Error al buscar productos", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBuyClick(Product product) {
        purchaseProduct(product);
    }


    private void purchaseProduct(Product product) {
        String userEmail = mAuth.getCurrentUser().getEmail(); // Usamos el email del usuario
        String purchaseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());


        Purchase purchase = new Purchase(
                product.getId(),
                product.getName(),
                product.getPrice(),
                purchaseDate,
                userEmail
        );


        DatabaseReference purchasesDatabase = FirebaseDatabase.getInstance().getReference("compras");
        String purchaseId = purchasesDatabase.push().getKey();
        if (purchaseId != null) {
            purchasesDatabase.child(purchaseId).setValue(purchase)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            deleteProductFromDatabase(product.getId());
                            Toast.makeText(SearchProductsActivity.this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SearchProductsActivity.this, "Error al realizar la compra", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    private void deleteProductFromDatabase(String productId) {
        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference("productos").child(productId);
        productRef.removeValue() //
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SearchProductsActivity.this, "Producto eliminado con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SearchProductsActivity.this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
