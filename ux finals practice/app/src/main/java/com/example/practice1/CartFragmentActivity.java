package com.example.practice1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.practice1.databinding.ActivityCartFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class CartFragmentActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCartFragmentBinding binding;

    public List<CartItem> activityCartItemList = new ArrayList<CartItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = getIntent();
//        if(intent != null) {
////            Bundle bundle = intent.getExtras();
//            activityCartItemList = intent.getParcelableArrayListExtra("CART_LIST");
//        }

        binding = ActivityCartFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);




        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartFragmentActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("CART_LIST", (ArrayList<CartItem>) activityCartItemList);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public List<CartItem>  getCartList(){
        return this.activityCartItemList;
    }
}