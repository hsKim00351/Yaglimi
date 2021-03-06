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

public class QuestionActivity extends AppCompatActivity {

    private static final String TAG = "Question";
    private RecyclerView rview;
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Toolbar toolbar = findViewById(R.id.my_toolbar4);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("질문 게시판");

        FloatingActionButton post_add = findViewById(R.id.postadd_btn);
        String input = "질문 게시판";
        post_add.setOnClickListener(v-> {
            Intent intentpost = new Intent(getApplicationContext(), PostActivity.class);
            intentpost.putExtra("text", input);
            startActivity(intentpost);
            finish();
        });

        rview = findViewById(R.id.post_listview);
        layoutManager = new LinearLayoutManager(this);
        rview.setLayoutManager(layoutManager);

        PostShow questionpost = new PostShow("question_posts", rview);
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
                Toast.makeText(getApplicationContext(), "이미 질문게시판 화면입니다.", Toast.LENGTH_SHORT).show();

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
