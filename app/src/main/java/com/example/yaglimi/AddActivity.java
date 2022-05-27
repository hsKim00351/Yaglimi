package com.example.yaglimi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private PillDB pillDB = null;
    private Context mContext;
    private EditText buydate;
    private EditText date;
    private EditText edit_pn;
    private Button save_btn;
    private RadioButton t_ra,b_ra,p_ra,s_ra,etc_ra;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        save_btn = findViewById(R.id.save_btn);
        buydate = findViewById(R.id.buydateText);
        date = findViewById(R.id.dateText);
        pillDB = PillDB.getInstance(this);
        mContext = getApplicationContext();

        t_ra = findViewById(R.id.t_ra);
        b_ra = findViewById(R.id.b_ra);
        p_ra = findViewById(R.id.p_ra);
        s_ra = findViewById(R.id.s_ra);
        etc_ra = findViewById(R.id.etc_ra);
        edit_pn = findViewById(R.id.edit_pn);

        class InsertRunnable implements Runnable {

            @Override
            public void run() {
                Pill pill = new Pill();

                String pn = null;
                if(t_ra.isChecked()) {
                    pn = t_ra.getText().toString();
                } else if(b_ra.isChecked()) {
                    pn = b_ra.getText().toString();
                }else if(p_ra.isChecked()) {
                    pn = p_ra.getText().toString();
                } else if(s_ra.isChecked()) {
                    pn = s_ra.getText().toString();
                } else if(etc_ra.isChecked()) {

                    pn = edit_pn.getText().toString();
                }

                pill.pillname = pn;
                pill.buydate = buydate.getText().toString();
                pill.date = date.getText().toString();
                PillDB.getInstance(mContext).pillDao().insertAll(pill);
            }
        }

        save_btn.setOnClickListener(v -> {

            InsertRunnable insertRunnable = new InsertRunnable();
            Thread addThread = new Thread(insertRunnable);
            addThread.start();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PillDB.destroyInstance();
    }
}
