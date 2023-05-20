package com.jahircelorio.consumo_api1;

import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText txttid, txtname, txtemail, txtbody, txtid_comentario;
    Button obtener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txttid = findViewById(R.id.id);
        txtname = findViewById(R.id.name);
        txtemail = findViewById(R.id.email);
        txtbody = findViewById(R.id.body);
        txtid_comentario = findViewById(R.id.id_comentario);
        obtener = findViewById(R.id.obtener);


        obtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this,"Prueba",Toast.LENGTH_SHORT).show();

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                String valor_id = txtid_comentario.getText().toString();



                String url = "https://jsonplaceholder.typicode.com/comments/" + valor_id;

                StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resObj = new JSONObject(response);
                            txttid.setText(resObj.getString("id"));
                            txtname.setText(resObj.getString("name"));
                            txtemail.setText(resObj.getString("email"));
                            txtbody.setText(resObj.getString("body"));
                        }catch(JSONException e){
                            throw  new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse:",error.getMessage());
                    }
                });

                requestQueue.add(jsonArrayRequest);
            }
        });
    }
}