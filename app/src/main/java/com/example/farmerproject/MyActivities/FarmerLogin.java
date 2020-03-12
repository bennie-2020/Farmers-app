package com.example.farmerproject.MyActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.farmerproject.Commons.DatabaseHelper;
import com.example.farmerproject.R;

import java.util.HashMap;
import java.util.Map;

import static com.example.farmerproject.Commons.Links.REGISTER;

public class FarmerLogin extends AppCompatActivity {
    EditText email1,confirmpassword1,password1,username;
    Button Register;
    DatabaseHelper db;
    static String TAG = "ms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);

        email1=findViewById(R.id.email);
        username=findViewById(R.id.username);
        confirmpassword1= findViewById(R.id.cpass);
        password1= findViewById(R.id.pass);
        Register=findViewById(R.id.register);
        db= new DatabaseHelper(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s1= email1.getText().toString();
                final String usernames= username.getText().toString();
                final String s2= confirmpassword1.getText().toString();
               final String s3= password1.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST,REGISTER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText( getApplicationContext(),"Register Successful",Toast.LENGTH_SHORT).show();
                        if(response !=null){
                            if(!response.isEmpty()){
                                if(response.equals("success")){
                                    Toast.makeText(FarmerLogin.this, "Registered successful", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(FarmerLogin.this,FarmerRealLog.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(FarmerLogin.this, "authethication failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d(TAG, "onErrorResponse: myerror"+error);
                                Toast.makeText(FarmerLogin.this, "myerror " +error, Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String,String> mymap = new HashMap<>();
                        mymap.put("email",s1);
                        mymap.put("username",usernames);
                        mymap.put("password",s3);
                        return mymap;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(FarmerLogin.this);
                requestQueue.add(sr);



//                String s1= email1.getText().toString();
//                String s2= confirmpassword1.getText().toString();
//                String s3= password1.getText().toString();

//                if (s1.equals("") || (s2.equals("")) || s3.equals("")){
//
//                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                    if (s2.equals(s3)){
//                        Boolean chkemail= db.chkemail(s1);
//                        if (chkemail==true){
//                            Boolean insert= db.insert(s1,s2);
//                            if (insert==true){
////                                Toast.makeText( getApplicationContext(),"Register Successful",Toast.LENGTH_SHORT).show();
////                                Intent intent= new Intent(FarmerLogin.this,FarmerRealLog.class);
////                                startActivity(intent);
//                            }
//                        }
//                        else
//                        {
//                            Toast.makeText( getApplicationContext(),"Email exists",Toast.LENGTH_SHORT).show();
//                        }

//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),"password does not match",Toast.LENGTH_SHORT).show();
//                    }
//                }

            }
        });
    }

    public void rdlogin(View view) {
        Intent intent= new Intent(FarmerLogin.this,FarmerRealLog.class);
        startActivity(intent);
    }
}
