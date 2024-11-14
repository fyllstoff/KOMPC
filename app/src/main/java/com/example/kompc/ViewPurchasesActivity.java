package com.example.kompc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPurchasesActivity extends AppCompatActivity {

    private ListView purchasesListView;
    private PurchaseAdapter purchaseAdapter;
    private ArrayList<Purchase> purchasesList;
    private DatabaseReference purchasesDatabaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchases);

        purchasesListView = findViewById(R.id.purchasesListView);
        purchasesList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();


        purchasesDatabaseReference = FirebaseDatabase.getInstance().getReference("compras");

        purchaseAdapter = new PurchaseAdapter(this, purchasesList);
        purchasesListView.setAdapter(purchaseAdapter);


        Button loadPurchasesButton = findViewById(R.id.loadPurchasesButton);
        loadPurchasesButton.setOnClickListener(view -> loadPurchases());
    }

    private void loadPurchases() {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
            return;
        }

        String userEmail = mAuth.getCurrentUser().getEmail();


        if (userEmail == null) {
            Toast.makeText(this, "Correo de usuario no disponible", Toast.LENGTH_SHORT).show();
            return;
        }

        
        purchasesDatabaseReference.orderByChild("userEmail").equalTo(userEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchasesList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Purchase purchase = snapshot.getValue(Purchase.class);
                            if (purchase != null) {
                                purchasesList.add(purchase);
                            }
                        }
                        purchaseAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(ViewPurchasesActivity.this, "Error al cargar las compras", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
