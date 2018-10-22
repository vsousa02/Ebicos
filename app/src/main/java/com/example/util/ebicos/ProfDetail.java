package com.example.util.ebicos;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.util.ebicos.Model.Prof;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfDetail extends AppCompatActivity {
    TextView profile_nome, profile_sobrenome, profile_profissao, profile_fixo, profile_celular,
            profile_pagamento, profile_h1, profile_h2, profile_h3, profile_h4, profile_h5, profile_h6;

    ImageView image_prof;

    CircleImageView img_user;

    CollapsingToolbarLayout collapsingToolbarLayout;

    String profId = "";

    FirebaseDatabase database;
    DatabaseReference profissional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_detail);

        database = FirebaseDatabase.getInstance();
        profissional = database.getReference("Prof");

        profile_nome = findViewById(R.id.profile_nome);
        profile_sobrenome = findViewById(R.id.profile_sobrenome);
        profile_profissao = findViewById(R.id.profile_profissao);
        profile_pagamento = findViewById(R.id.profile_pagamento);
        profile_fixo = findViewById(R.id.profile_fixo);
        profile_celular = findViewById(R.id.profile_celular);
        profile_h1 = findViewById(R.id.profile_h1);
        profile_h2 = findViewById(R.id.profile_h2);
        profile_h3 = findViewById(R.id.profile_h3);
        profile_h4 = findViewById(R.id.profile_h4);
        profile_h5 = findViewById(R.id.profile_h5);
        profile_h6 = findViewById(R.id.profile_h6);

        image_prof = findViewById(R.id.image_prof);

        img_user = findViewById(R.id.img_user);

        collapsingToolbarLayout = findViewById(R.id.collapsing);


        if (getIntent() != null)
            profId = getIntent().getStringExtra("ProfId");
        if (!profId.isEmpty()) {

            getDetailProf(profId);
        }


    }

    private void getDetailProf(String profId) {
        profissional.child(profId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Prof prof = dataSnapshot.getValue(Prof.class);

                Picasso.with(getBaseContext()).load(prof.getImagem())
                        .into(image_prof);

                Picasso.with(getBaseContext()).load(prof.getImagem())
                        .into(img_user);

                profile_nome.setText(prof.getNome());
                profile_sobrenome.setText(prof.getSobrenome());
                profile_profissao.setText(prof.getProfissao());
                profile_pagamento.setText(prof.getPagamento());
                profile_fixo.setText(prof.getFixo());
                profile_celular.setText(prof.getCelular());
                profile_h1.setText(prof.getH1());
                profile_h2.setText(prof.getH2());
                profile_h3.setText(prof.getH3());
                profile_h4.setText(prof.getH4());
                profile_h5.setText(prof.getH5());
                profile_h6.setText(prof.getH6());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
