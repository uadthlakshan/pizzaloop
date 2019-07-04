package com.example.pizzaloop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemClickListner  {

    private static final String URL = "http://"+IPAddress.IPAddress+":8080/demo/cart";

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    List<Cart> cartlist;

    Button pay;
    double allprice = 0.0;

    String imageurl;
    String name;
    String description;
    Double price;
    Double smallprice;
    Double mediumprice;
    Double largeprice;
    String PName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartlist = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pay = findViewById(R.id.pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this,"Order Places Successfully", Toast.LENGTH_LONG ).show();
            }
        });


        loadcart();


    }

    private void loadcart() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONArray products = new JSONArray(response);

                    for (int i = 0; i < products.length(); i++) {

                        JSONObject productobject = products.getJSONObject(i);

                        int cartId = productobject.getInt("cartId");
                        String imageUrl = productobject.getString("imageUrl");
                        String name = productobject.getString("name");
                        double price = productobject.getDouble("price");


                        Cart product = new Cart(cartId, imageUrl, name, price);
                        cartlist.add(product);


                    }

                    adapter = new CartAdapter(CartActivity.this, (ArrayList<Cart>) cartlist);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(CartActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);


    }

    @Override
    public void onItemClick(int position) {
        Cart clickedItem = cartlist.get(position);

        int id = clickedItem.getCartId();

        String URL1 = "http://" + IPAddress.IPAddress + ":8080/demo/deleteByCartId?id=" + id + "";



        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

        System.out.println("2222222222222222222222222222222222222222222222222"+URL1);

        finish();
        startActivity(getIntent());


    }
}

