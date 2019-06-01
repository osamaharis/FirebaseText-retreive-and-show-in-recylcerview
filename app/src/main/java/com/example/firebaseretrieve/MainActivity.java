package com.example.firebaseretrieve;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("All Recipes");

       databaseReference=FirebaseDatabase.getInstance().getReference().child("global");
       databaseReference.keepSynced(true);
       recyclerView = findViewById(R.id.recyclerview);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<data ,Holderhold> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<data, Holderhold>
                (data.class,R.layout.datarows,Holderhold.class,databaseReference) {
            @Override
            protected void populateViewHolder(Holderhold viewHolder, data model, int position) {

                viewHolder.settile(model.getTitle());
                viewHolder.setdec(model.getDescription());
                viewHolder.setImages(getApplicationContext(),model.getImage() );



            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    //this is holder class for holding images  recipe name and details///////

    public static class Holderhold extends RecyclerView.ViewHolder
    {


        View view;
        public Holderhold(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }
        public void settile(String recip_name)
        {
            TextView recipename= (TextView)view.findViewById(R.id.recipename);
            recipename.setText(recip_name);
        }
        public void setdec(String description)
        {
            TextView recipedetail=(TextView) view.findViewById(R.id.detail);
            recipedetail.setText(description);
        }

        public void setImages(Context con,String image)
        {
            ImageView imagess =(ImageView) view.findViewById(R.id.imageview);
            Picasso.get().load(image).into(imagess);

        }
    }
}
