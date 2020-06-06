package com.ashishchidhava.firebase_third;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadMultiplePost extends AppCompatActivity {
    private static final String TAG = "ReadMultiplePost";
TextView text;
private List<Post> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_multiple_post);
        text=findViewById(R.id.text);

        FirebaseDatabase.getInstance().getReference()
                .child("Post")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String s="";
//                        list= new ArrayList<>();
//                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//
//                            s=s+"Title : "+postSnapshot.child("Title").getValue().toString()+"\n"+
//                                   "\n Descirption"+postSnapshot.child("Description").getValue().toString()+"\n"+
//                                    "\n Author"+postSnapshot.child("Author").getValue().toString();
//
//                            Post post=postSnapshot.getValue(Post.class);
//                            list.add(post);
//                        }
//                        Log.i(TAG, "onDataChange: "+list.toString());
//                        text.setText(s);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String s="";
//                        list= new ArrayList<>();
//                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//
//                            s=s+"Title : "+postSnapshot.child("Title").getValue().toString()+"\n"+
//                                    "\n Descirption"+postSnapshot.child("Description").getValue().toString()+"\n"+
//                                    "\n Author"+postSnapshot.child("Author").getValue().toString();
//
//                            Post post=postSnapshot.getValue(Post.class);
//                            list.add(post);
//                        }
//                        Log.i(TAG, "onDataChange: "+list.toString());
//                        text.setText(s);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String s="";
                        list= new ArrayList<>();
                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                            s=s+"Title : "+postSnapshot.child("Title").getValue().toString()+"\n"+
                                    "\n Descirption"+postSnapshot.child("Description").getValue().toString()+"\n"+
                                    "\n Author"+postSnapshot.child("Author").getValue().toString();

                            Post post=postSnapshot.getValue(Post.class);
                            list.add(post);
                        }
                        Log.i(TAG, "onDataChange: "+list.toString());
                        text.setText(s);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
