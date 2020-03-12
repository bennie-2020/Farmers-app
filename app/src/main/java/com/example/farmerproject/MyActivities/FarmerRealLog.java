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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

import static com.example.farmerproject.Commons.Links.LOGIN;

public class FarmerRealLog extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    DatabaseHelper db;
    public static  final String USERNAME="user";

    static String TAG = "ms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_real_log);


        Paper.init(FarmerRealLog.this);

        email=(EditText) findViewById(R.id.Farmeremail);
        password=(EditText) findViewById(R.id.Farmerpassword);
        login=(Button) findViewById(R.id.Farmerlogin);
        db= new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final  String s1 =email.getText().toString();
               final String s2= password.getText().toString();

                StringRequest sr=new StringRequest(Request.Method.POST,LOGIN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(FarmerRealLog.this, "hello" + response, Toast.LENGTH_SHORT).show();
                        Paper.book().write(USERNAME,s1);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ans = jsonObject.getString("success");

                            if(ans.equals("success")){
                                Toast.makeText(FarmerRealLog.this, "login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(FarmerRealLog.this,FarmerMainPage.class));
                                finish();
                            }
                            else{
                                Toast.makeText(FarmerRealLog.this, "User not registered", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d(TAG, "onErrorResponse: myerror"+error);
                                Toast.makeText(FarmerRealLog.this, "myerror " +error, Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String,String> mymap = new HashMap<>();
                        mymap.put("email",s1);
                        mymap.put("password",s2);
                        return mymap;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(FarmerRealLog.this);
                requestQueue.add(sr);










//                Boolean chkemailpass= db.emailpassword(s1,s2);
//
//                if (chkemailpass==true){
//                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
//                    Intent intent= new Intent(FarmerRealLog.this,FarmerMainPage.class);
//                   startActivity(intent);
//                }else {
//                    Toast.makeText(getApplicationContext(),"Invalid details",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public void rdregister(View view) {
        Intent intent= new Intent(FarmerRealLog.this,FarmerLogin.class);
        startActivity(intent);
    }
}
