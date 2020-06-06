package com.ashishchidhava.firebase_third;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class PostAdapter extends FirebaseRecyclerAdapter<Post, PostAdapter.PostViewHolder> {
    private  Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options , Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostViewHolder holder, final int position, @NonNull final Post model) {



        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.author.setText(model.getAuthor());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FirebaseDatabase.getInstance().getReference()
                    .child("Post")
                    .child(getRef(position).getKey())
                    .removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           
                        }
                    });
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.content))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View holderView = (LinearLayout)dialog.getHolderView();
               final EditText title= holderView.findViewById(R.id.u_title);
               final EditText description=holderView.findViewById(R.id.u_description);
               final EditText author=holderView.findViewById(R.id.u_author);
               Button update=holderView.findViewById(R.id.update);
               Button delete=holderView.findViewById(R.id.delete);

                title.setText(model.getTitle());
                description.setText(model.getDescription());
                author.setText(model.getAuthor());
               update.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Map<String,Object> map=new HashMap<>();
                       map.put("Title",title.getText().toString());
                       map.put("Description",description.getText().toString());
                       map.put("Author",author.getText().toString());
                       FirebaseDatabase.getInstance().getReference()
                               .child("Post")
                               .child( getRef(position).getKey())
                               .updateChildren(map)
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       dialog.dismiss();

                                   }
                               });
                   }
               });


                dialog.show();
            }
        });
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post, parent, false);

        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,author;
        ImageView edit=itemView.findViewById(R.id.edit);
        ImageView delete=itemView.findViewById(R.id.delete);
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            author=itemView.findViewById(R.id.author);
        }
    }
}
