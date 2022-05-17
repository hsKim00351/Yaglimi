package com.example.yaglimi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText buydateText, dateText;
    private Button save_btn, add_btn;
    private RadioButton one_ra, two_ra, three_ra, four_ra;
    private PillDB pillDB = null;
    private List<Pill> pillList;
    private Context pContext = null;
    private PillAdapter pillAdapter;
    private RecyclerView pRecyclerView;
    private TextView user_id;
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("약리미");

        pRecyclerView = (RecyclerView) findViewById(R.id.pRecyclerView);
        pContext = getApplicationContext();
        pillAdapter = new PillAdapter(pillList);

        pillDB = PillDB.getInstance(this);

        class InsertRunnable implements Runnable {
            @Override
            public void run() {
                try {
                    pillList = PillDB.getInstance(pContext).pillDao().getAll();
                    pillAdapter = new PillAdapter(pillList);
                    pillAdapter.notifyDataSetChanged();

                    pRecyclerView.setAdapter(pillAdapter);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(pContext, RecyclerView.VERTICAL, false);
                    pRecyclerView.setLayoutManager(mLinearLayoutManager);
                } catch (Exception e) {

                }
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplicationContext(), AddActivity.class);
            //addIntent = new Intent(getApplicationContext(),AddActivity.class);
            startActivity(intent2);
            finish();
        });

        //drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        //drawerView = (View)findViewById(R.id.drawer_ex);

        /*user_id = findViewById(R.id.username);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        user_id.setText(userID);*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.share:
                Intent intentshare= new Intent(getApplicationContext(), ShareActivity.class);
                startActivity(intentshare);
                return super.onOptionsItemSelected(item);
                //finish();
                //Toast.makeText(getApplicationContext(), "나눔게시판 화면입니다.", Toast.LENGTH_SHORT).show();
            case R.id.information:
                Intent intentinfo = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intentinfo);
                return super.onOptionsItemSelected(item);
                //finish();
            case R.id.question:
                Intent intentque = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intentque);
                return super.onOptionsItemSelected(item);
                //finish();
            case R.id.etc:
                Intent intentetc = new Intent(getApplicationContext(), EtcActivity.class);
                startActivity(intentetc);
                return super.onOptionsItemSelected(item);
                //finish();
            case R.id.home:
                Toast.makeText(getApplicationContext(), "이미 홈 화면입니다.", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PillDB.destroyInstance();
        pillDB = null;
    }
}