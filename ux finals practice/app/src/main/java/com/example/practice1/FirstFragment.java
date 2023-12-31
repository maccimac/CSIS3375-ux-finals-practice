package com.example.practice1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.practice1.databinding.FragmentFirstBinding;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    List<SaleItem> forSaleList = new ArrayList<>();
    private FragmentFirstBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        // START HERE
        Log.i("INS", "Start");
        fetchBikeData(); // ANNOTATE WITH @RequiresApi(api = Build.VERSION_CODES.O)

        forSaleList.forEach(((saleItem)->{
            Log.i("ITEM", saleItem.name + " " + saleItem.itemDate);
        }));

        SaleItemAdapter saleItemAdapter = new SaleItemAdapter(forSaleList, new SaleItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {

                Log.d("INTENT ATTEMPT", "Should move to new act");
                Intent intent = new Intent(
                        container.getContext(), CartFragmentActivity.class
                );
                SaleItem saleToCart = forSaleList.get(i);
                intent.putExtra("SALE_TO_CART", saleToCart);
                MainActivity parentActivity = (MainActivity) getActivity();
                intent.putParcelableArrayListExtra("CART_LIST", (ArrayList<CartItem>) parentActivity.cartItemList);
//                intent.putExtra("TEXT", "Text");
                startActivity(intent);
            }

            @Override
            public void extraMethod(SaleItem item) {
                    checkItemDetail(item);
            }
//
        });

        // MAKE SURE TO LAYOUT OR GRID MANAGER
        binding.recyeclerItemsHolder.setLayoutManager(new LinearLayoutManager(container.getContext()));
        binding.recyeclerItemsHolder.setAdapter(saleItemAdapter);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//
//        });
    }

    public void checkItemDetail(SaleItem item){

        Bundle bundle = new Bundle();
        bundle.putString(
                "ITEM_NAME", item.name
        );
        bundle.putString(
                "ITEM_DESCRIPTION", item.description
        );
        bundle.putInt(
                "ITEM_IMAGE", item.imageId
        );
        bundle.putDouble(
                "ITEM_PRICE", item.price
        );

        bundle.putParcelable("SALE_TO_CART", item);
        MainActivity parentActivity = (MainActivity) getActivity();
        bundle.putParcelableArrayList("CART_LIST", (ArrayList<CartItem>) parentActivity.cartItemList);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void fetchBikeData(){
        forSaleList = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.indoor_bikes);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        try {
            String csvLine;

            // with header
            if((csvLine = reader.readLine())!= null){

            }

            while((csvLine = reader.readLine())!= null){
                String[] itemValues = csvLine.split(",");

                String itemName = itemValues[0];
                double itemPrice = Double.parseDouble(itemValues[1]);
                String itemDateStr = itemValues[2];
                String itemDesc = itemValues[3];
                String itemUrl = itemValues[4];

                SaleItem singleBikeSaleItem = new SaleItem(
                        itemName,
                        itemDesc,
                        itemPrice,
                        itemUrl
                );


//              int itemImageDrawable = getResources().getIdentifier(
//                        itemName.toLowerCase(), "drawable", getPackageName());
                int imageId = getResources().getIdentifier(
                        itemName.toLowerCase(), "drawable", this.getContext().getPackageName()
                );
                singleBikeSaleItem.setImageId(imageId);


                // ENSURE TO ANNOTATE WITH:   @RequiresApi(api = Build.VERSION_CODES.O)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate itemDate = LocalDate.parse(itemDateStr, formatter);
                //d - one or more digits for date e.g., 8, 16, 31
                //MMM - three letter month code e.g., SEP, MAY, JUN
                //yyyy - 4 digit year e.g., 2023, 1945, 1632

                //dd - two digits for date e.g., 08, 16, 31
                //MM - month number e.g., 01, 05, 12
                //yy - 2 digit year number e.g., 23, 45, 32

                singleBikeSaleItem.setItemDate(itemDate);
                forSaleList.add(singleBikeSaleItem);
            }


        }catch(Exception e){
            Log.d("[fetchBikeData]", e.getMessage());
        }


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}