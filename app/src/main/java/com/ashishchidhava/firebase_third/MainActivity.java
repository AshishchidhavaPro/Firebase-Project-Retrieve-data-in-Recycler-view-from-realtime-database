package com.ashishchidhava.firebase_third;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText title, desc, author;
    TextView text;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        author=findViewById(R.id.author);
        text=findViewById(R.id.text);
    }
    public void submit(View view) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("Title",title.getText().toString());
        map.put("Description",  desc.getText().toString());
        map.put("Author", author.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Post").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Data Send Successfully", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onSuccess: Data Sed");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: Failed");
                        Toast.makeText(MainActivity.this, "Data Sended failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void Recieve(View view) {
        FirebaseDatabase.getInstance().getReference().child("Post").child("-M8zFkeJu9qPajAT2S7j").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String post="Title : "+dataSnapshot.child("Title").getValue(String.class)+"\n"+
                                "Description : "+dataSnapshot.child("Description").getValue(String.class)+"\n"+
                                "Author : "+dataSnapshot.child("Author").getValue(String.class);
                                text.setText(post);
                            
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Data Reciving failed", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onCancelled: Failed");
                    }
                });
    }

    public void recycle(View view) {
        Intent intent=new Intent(MainActivity.this,PostActivity.class);
        startActivity(intent);

    }
}
