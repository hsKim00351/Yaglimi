package com.example.yaglimi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.ViewHolder> {
    private List<Pill> pillList;
    private PillDB db;
    private Activity context;
    public PillAdapter(List<Pill> list) {
        pillList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_item, parent, false);
        PillAdapter.ViewHolder pillvh = new PillAdapter.ViewHolder(view);

        return pillvh;
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pill item = pillList.get(position);
        db = PillDB.getInstance(context);
        holder.pillname.setText(item.pillname);
        holder.buydate.setText(item.buydate);
        holder.date.setText(item.date);

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pill selectpill = pillList.get(holder.getAdapterPosition());

                class InsertRunnable implements Runnable {
                    @Override
                    public void run() {
                        try {
                            db.pillDao().delete(selectpill);
                        } catch (Exception e) {

                        }
                    }
                }

                InsertRunnable insertRunnable = new InsertRunnable();
                Thread t = new Thread(insertRunnable);
                t.start();

                int position = holder.getAdapterPosition();
                pillList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, pillList.size());
            }
        });
    }
    @Override
    public int getItemCount() {
        return pillList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pillname;
        TextView buydate;
        TextView date;
        ImageButton delete_btn;

        public ViewHolder(View itemView) {
            super(itemView);

            pillname = itemView.findViewById(R.id.item_name);
            buydate = itemView.findViewById(R.id.item_buydate);
            date =itemView.findViewById(R.id.item_date);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }
    }
}
