package com.example.practice1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.practice1.databinding.FragmentSecondBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SecondFragment extends Fragment {

    List<ColorSpec> colorOptionsList;
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        if(getArguments()!= null){
            binding.textViewDetailName.setText(getArguments().getString("ITEM_NAME", "Bike"));
            binding.textViewDetailPrice.setText(
                    "$" +
                    String.valueOf(getArguments().getDouble("ITEM_PRICE", 0))
                    // String.toString
                    // obj.toString()
            );
            binding.imageDetailImage.setImageResource(
                    getArguments().getInt("ITEM_IMAGE", 1)
            );
            binding.textViewDetailDesc.setText(
                    getArguments().getString("ITEM_DESCRIPTION", "Stationary bike")
            );
        }

        binding.spinnerDetailColor.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // set color of button
                        Snackbar.make(view, "This ok?", Snackbar.LENGTH_LONG)
                                .setAnchorView(R.id.image_detail_image)
                                .setAction(
                                        "OK",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Toast.makeText(view.getContext(), "Got it", Toast.LENGTH_LONG ).show();
                                            }
                                        }
                                ).show();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void addToCart(){

    }

}