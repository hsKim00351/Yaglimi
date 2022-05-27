package com.example.yaglimi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yaglimi.views.Writeinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ShareActivity extends AppCompatActivity {

    private static final String TAG = "Share";
    private RecyclerView rview;
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Toolbar toolbar = findViewById(R.id.my_toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("상비약 나눔 게시판");

        FloatingActionButton post_add = findViewById(R.id.postadd_btn);
        String input = "상비약 나눔게시판";
        post_add.setOnClickListener(v-> {
            Intent intentpost = new Intent(getApplicationContext(), PostActivity.class);
            intentpost.putExtra("text", input);
            startActivity(intentpost);
        });

        rview = findViewById(R.id.post_listview);
        layoutManager = new LinearLayoutManager(this);
        rview.setLayoutManager(layoutManager);

        PostShow sharepost = new PostShow("share_posts", rview);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(getApplicationContext(), "이미 상비약 나눔게시판 화면입니다.", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);

            case R.id.information:
                Intent intentinfo = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intentinfo);
                finish();
                return super.onOptionsItemSelected(item);
            case R.id.question:
                Intent intentque = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intentque);
                finish();
                return super.onOptionsItemSelected(item);
            case R.id.etc:
                Intent intentetc = new Intent(getApplicationContext(), EtcActivity.class);
                startActivity(intentetc);
                finish();
                return super.onOptionsItemSelected(item);
            case R.id.home:
                Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intenthome);
                finish();
                return super.onOptionsItemSelected(item);

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

