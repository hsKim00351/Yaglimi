package com.example.yaglimi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EtcActivity extends AppCompatActivity {

    private static final String TAG = "Etc";
    private RecyclerView rview;
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc);

        Toolbar toolbar = findViewById(R.id.my_toolbar5);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("기타 게시판");

        String input = "기타 게시판";

        FloatingActionButton post_add = findViewById(R.id.postadd_btn);
        post_add.setOnClickListener(v-> {
            Intent intentpost = new Intent(getApplicationContext(), PostActivity.class);
            intentpost.putExtra("text", input);
            startActivity(intentpost);
            finish();
        });

        rview = findViewById(R.id.post_listview);
        layoutManager = new LinearLayoutManager(this);
        rview.setLayoutManager(layoutManager);

        PostShow etcpost = new PostShow("etc_posts", rview);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.share:
                Intent intentshare = new Intent(getApplicationContext(), ShareActivity.class);
                startActivity(intentshare);
                finish();
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
                Toast.makeText(getApplicationContext(), "이미 질문게시판 화면입니다.", Toast.LENGTH_SHORT).show();

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

