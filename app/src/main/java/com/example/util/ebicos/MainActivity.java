package com.example.util.ebicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_profissional, btn_encontrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_profissional = findViewById(R.id.btn_profissional);
        btn_encontrar = findViewById(R.id.btn_encontrar);

        btn_profissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Cadastrar Profissional", Toast.LENGTH_SHORT).show();
            }
        });

        btn_encontrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MainActivity.this, Home.class);
                startActivity(home);

            }
        });
    }
}
