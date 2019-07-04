package com.example.pizzaloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String name = "l";
    String pw = "1";

    EditText username, password;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);

        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+password.getText());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+username.getText());



                if(username.getText().toString().equals(name) && password.getText().toString().equals(pw)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"User Name or Password is Wrong!",Toast.LENGTH_LONG).show();
                }

            }
        });






    }

    public void login(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
