package com.example.practice1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.practice1.databinding.FragmentCartFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class CartFirstFragment extends Fragment {

    List<CartItem> fragCartItemList = new ArrayList<>();
    private FragmentCartFirstBinding binding;
    CartFragmentActivity parentActivity;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        parentActivity = (CartFragmentActivity) getActivity();
        if(parentActivity.activityCartItemList != null){
            this.fragCartItemList = parentActivity.getCartList();
        }
        Intent intent = getActivity().getIntent();
        if(intent != null){
//            Bundle bundle = intent.getExtras();
            List newCartList = intent.getParcelableArrayListExtra("CART_LIST");
            if(newCartList != null){
                fragCartItemList= newCartList;
            }
            SaleItem saleToCart =  intent.getParcelableExtra("SALE_TO_CART");
            int amount = intent.getIntExtra("SALE_AMOUNT", 1);
//            String amountStr = intent.getStringExtra("SALE_AMOUNT");
//            int amount = Integer.parseInt(amountStr);

            if(saleToCart != null){
                fragCartItemList.add(
                        new CartItem(saleToCart, amount)
                );

            }

        }else{
            fragCartItemList.add(
                new CartItem()
            );
        }
        

        parentActivity.activityCartItemList = fragCartItemList;
        binding = FragmentCartFirstBinding.inflate(inflater, container, false);
        binding.listviewCartItems.setAdapter(new CartItemAdapter(fragCartItemList));
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CartItemViewModel cartItemViewModel = new ViewModelProvider(requireActivity())
                .get(CartItemViewModel.class);
        cartItemViewModel.getCartItemList()
                        .observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
                            @Override
                            public void onChanged(List<CartItem> cartItemList) {
                                fragCartItemList = cartItemList;
                                binding.listviewCartItems.setAdapter(new CartItemAdapter(
                                        fragCartItemList
                                ));
                            }
                        });



        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CartFirstFragment.this)
                        .navigate(R.id.action_cart1_to_cart2);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}