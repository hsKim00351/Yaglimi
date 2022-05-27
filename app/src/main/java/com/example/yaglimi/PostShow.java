package com.example.yaglimi;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaglimi.views.Writeinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class PostShow {
    private static final String TAG = "PostShow";

    private String postname;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Map<String, Object> info;
    private ArrayList<Map<String, Object>> list;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Writeinfo> itemList = new ArrayList<>();
    private RecyclerView rview;

    public PostShow(String postname, RecyclerView rview){

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(postname);

        Writeinfo [] writeinfos = new Writeinfo[100];

        String [] t = new String[20];
        String [] p = new String[20];
        String [] ti = new String[20];
        String [] c = new String[100];

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(postname)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(int j=0; j<1;j++) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    info = document.getData();

                                    t[j] = (String) info.get("title");
                                    p[j] = (String) info.get("publisher");
                                    ti[j] = (String) info.get("time");
                                    c[j] = (String) info.get("content");

                                    writeinfos[j] = new Writeinfo(t[j],c[j],p[j],ti[j]);
                                    itemList.add(writeinfos[j]);

                                    adapter = new PostAdapter(itemList);
                                    rview.setAdapter(adapter);
                                    j++;

                                }
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
