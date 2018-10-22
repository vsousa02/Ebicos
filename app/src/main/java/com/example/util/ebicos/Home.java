package com.example.util.ebicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.util.ebicos.Interface.ItemClickListener;
import com.example.util.ebicos.Model.Category;
import com.example.util.ebicos.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity {
    private Toolbar toolbar_home;

    FirebaseDatabase database;
    DatabaseReference category;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category, MenuViewHolder>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar_home = findViewById(R.id.toolbar_home);
        toolbar_home.setTitle("Serviços");
        setSupportActionBar(toolbar_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //INICIANDO FIREBASE
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        //CARREGANDO MENU
        recycler_menu = findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();

    }

    private void loadMenu() {

        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>
                (Category.class,
                        R.layout.menu_item,
                        MenuViewHolder.class,
                        category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                viewHolder.menu_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.menu_image);

                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent profList = new Intent(Home.this, ProfList.class);
                        profList.putExtra("CategoryId", adapter.getRef(position).getKey());
                        startActivity(profList);
                    }
                });

            }
        };
        recycler_menu.setAdapter(adapter);


    }
}
