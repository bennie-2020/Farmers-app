package com.example.farmerproject.MyActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.farmerproject.R;

public class logins extends Fragment {

    Intent intent;
    Intent intent1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.logins , container , false);

        intent = new Intent(getActivity(), FarmerLogin.class);
        final Button button = (Button) view.findViewById(R.id.farmerlogin);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        intent1 = new Intent(getActivity(), SupplierLogin.class);
        final Button supplier = (Button) view.findViewById(R.id.supplierlogin);
        supplier.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        return view;



    }


}
