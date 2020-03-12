package com.example.farmerproject.MyActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmerproject.R;

public class FarmerMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_main_page);
    }

    public void rdcomplaint(View view) {
        startActivity(new Intent(FarmerMainPage.this,complaint.class));



    }

    public void rdshop(View view) {
        Intent intent= new Intent(FarmerMainPage.this,Farmshop.class);
        startActivity(intent);
    }


    public void rduser(View view) {
        Intent intent= new Intent(FarmerMainPage.this, Test.class);
        startActivity(intent);
    }

    public void rdcomplaint_status(View view) {
        Intent intent= new Intent(FarmerMainPage.this,Complaint_status.class);
        startActivity(intent);
    }
}
