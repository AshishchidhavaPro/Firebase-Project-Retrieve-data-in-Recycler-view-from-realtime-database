package com.ashishchidhava.firebase_third;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {
private RecyclerView recyclerview;
private PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        recyclerview=findViewById(R.id.recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Post"), Post.class)
                        .build();

        postAdapter=new PostAdapter(options);
        recyclerview.setAdapter(postAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        postAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        postAdapter.stopListening();
    }
}
