package com.example.yaglimi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yaglimi.views.Writeinfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<Writeinfo> postlist;
    private TextView title_text, content_text, time_text,publisher_text;
    private Activity activity;
    private Context context;

    public PostAdapter(ArrayList<Writeinfo> postlist) {
        this.postlist = postlist;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_post, parent, false);
        PostAdapter.ViewHolder postvh = new PostAdapter.ViewHolder(view);

        return postvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.title.setText(postlist.get(position).getTitle());
        holder.publisher.setText(postlist.get(position).getPublisher());
        holder.time.setText(postlist.get(position).getTime());
        holder.content.setText(postlist.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView publisher;
        TextView time;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTextView);
            publisher = itemView.findViewById(R.id.publisherTextView);
            time =itemView.findViewById(R.id.timeTextView);
            content = itemView.findViewById(R.id.contentTextView);
        }
    }
}


