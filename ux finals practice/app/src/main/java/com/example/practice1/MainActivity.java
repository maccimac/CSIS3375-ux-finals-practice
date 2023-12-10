package com.example.practice1;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.practice1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    List<Item> forSaleList = new ArrayList<>();

//    [] fetch from csv
//    [] create adapter
//    [] add to cart moves to detail fragment
//    [] transfer data to detail fragment
//    [] show detail fragmen info
//

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // START HERE
        Log.i("INS", "Start");
        fetchBikeData(); // ANNOTATE WITH @RequiresApi(api = Build.VERSION_CODES.O)

        forSaleList.forEach(((item)->{
            Log.i("ITEM", item.name + " " + item.itemDate);
        }));


        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void fetchBikeData(){
//        forSaleList = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.indoor_bikes);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        try {
            String csvLine;

            // with header
//            if((csvLine = reader.readLine())!= null){
//
//            }

            while((csvLine = reader.readLine())!= null){
                String[] itemValues = csvLine.split(",");

                String itemName = itemValues[0];
                double itemPrice = Double.parseDouble(itemValues[1]);
                String itemDateStr = itemValues[2];
                String itemDesc = itemValues[3];
                String itemUrl = itemValues[4];

//                int itemImageDrawable = getResources().getIdentifier(
//                        itemName.toLowerCase(), "drawable", getPackageName()
//                );

                Item singleBikeItem = new Item(
                        itemName,
                        itemDesc,
                        itemPrice,
                        itemUrl
                );

                // ENSURE TO ANOTATE WITH:   @RequiresApi(api = Build.VERSION_CODES.O)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate itemDate = LocalDate.parse(itemDateStr, formatter);
                //d - one or more digits for date e.g., 8, 16, 31
                //MMM - three letter month code e.g., SEP, MAY, JUN
                //yyyy - 4 digit year e.g., 2023, 1945, 1632

                //dd - two digits for date e.g., 08, 16, 31
                //MM - month number e.g., 01, 05, 12
                //yy - 2 digit year number e.g., 23, 45, 32

                singleBikeItem.setItemDate(itemDate);
                forSaleList.add(singleBikeItem);
            }


        }catch(Exception e){
            Log.d("[fetchBikeData]", e.getMessage());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}