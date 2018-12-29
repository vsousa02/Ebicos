package com.example.util.ebicos;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.util.ebicos.Interface.ItemClickListener;
import com.example.util.ebicos.Model.Prof;
import com.example.util.ebicos.ViewHolder.ProfViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ProfList extends AppCompatActivity {

    private Toolbar toolbar_home;


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference profList;

    String categoryId = "";



    FirebaseRecyclerAdapter <Prof, ProfViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_list);
        toolbar_home = findViewById(R.id.toolbar_home);
        toolbar_home.setTitle("Profissionais");
        setSupportActionBar(toolbar_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        database = FirebaseDatabase.getInstance();
        profList = database.getReference("Prof");




        recyclerView = findViewById(R.id.recycler_prof);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null){

            loadListProf(categoryId);
        }
    }

    private void loadListProf(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Prof, ProfViewHolder>(Prof.class,
                R.layout.prof_item,
                ProfViewHolder.class,
                profList.orderByChild("MenuId").equalTo(categoryId)
        ) {
            @Override
            protected void populateViewHolder(ProfViewHolder viewHolder, Prof model, int position) {
                viewHolder.item_name.setText(model.getNome());
                viewHolder.item_sobrenome.setText(model.getSobrenome());
                viewHolder.item_prof.setText(model.getProfissao());
                //viewHolder.item_desc.setText(model.getDescricao());

                Picasso.with(getBaseContext()).load(model.getImagem())
                        .into(viewHolder.item_image);

                final Prof local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent profDetail = new Intent(ProfList.this, ProfDetail.class);
                        profDetail.putExtra("ProfId", adapter.getRef(position).getKey());
                        startActivity(profDetail);
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);

    }
}