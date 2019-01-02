package com.example.util.ebicos;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.util.ebicos.Model.Prof;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfDetail extends AppCompatActivity {


    TextView profile_nome, profile_sobrenome, profile_descricao;

    ImageView image_prof, ic_favorito, ic_whats, ic_view;

    CircleImageView img_user;

    String profId = "";
    String celular;


    FirebaseDatabase database;
    DatabaseReference profissional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_detail);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);





        database = FirebaseDatabase.getInstance();
        profissional = database.getReference("Prof");

        profile_nome = findViewById(R.id.profile_nome);
        profile_sobrenome = findViewById(R.id.profile_sobrenome);
        img_user = findViewById(R.id.img_user);
        ic_favorito = findViewById(R.id.img_fav);
        ic_whats = findViewById(R.id.img_whats);
        ic_view = findViewById(R.id.img_view);



        if (getIntent() != null)
            profId = getIntent().getStringExtra("ProfId");
        if (!profId.isEmpty()) {

            getDetailProf(profId);
        }


        ic_whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+celular));
                    startActivity(intent);

                }
                catch (Exception e){
                    e.printStackTrace();

                }

            }
        });


    }

    private void getDetailProf(String profId) {
        profissional.child(profId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Prof prof = dataSnapshot.getValue(Prof.class);


                Picasso.with(getBaseContext()).load(prof.getImagem())
                        .into(img_user);

                profile_nome.setText(prof.getNome());
                profile_sobrenome.setText(prof.getSobrenome());

                celular = prof.getCelular();

//                profile_celular.setText(prof.getCelular());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
