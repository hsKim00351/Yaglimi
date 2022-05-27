package com.example.yaglimi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yaglimi.views.Writeinfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostActivity extends AppCompatActivity {
    private TextView post_activity;
    private EditText post_title, post_content;
    private Button post_confirm;
    private FirebaseUser user;
    private String text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post_activity = findViewById(R.id.post_activity);
        post_title = findViewById(R.id.post_title);
        post_content = findViewById(R.id.post_content);
        post_confirm = findViewById(R.id.post_confirm);

        Intent intent = getIntent();
        text = intent.getStringExtra("text");
        post_activity.setText(text);

        post_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat Format = new SimpleDateFormat("MM월dd일 hh:mm");
                String getTime = Format.format(date);

                final String title = post_title.getText().toString();
                final String content = post_content.getText().toString();

                if (title.length() > 0 && content.length() > 0) {
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    Writeinfo wi = new Writeinfo(title, content,user.getUid(),getTime);
                    upload(wi);
                } else {
                    Toast.makeText(getApplicationContext(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void upload(Writeinfo writeinfo) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if(text.equals("상비약 나눔게시판")){
            db.collection("share_posts").add(writeinfo)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(PostActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                            Intent intentshare = new Intent(getApplicationContext(), ShareActivity.class);
                            startActivity(intentshare);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
                }
            });
        } else if(text.equals("정보 게시판")){
            db.collection("info_posts").add(writeinfo)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(PostActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                            Intent intentinfo = new Intent(getApplicationContext(), InformationActivity.class);
                            startActivity(intentinfo);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(text.equals("질문 게시판")) {
            db.collection("question_posts").add(writeinfo)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(PostActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                            Intent intentquestion = new Intent(getApplicationContext(), QuestionActivity.class);
                            startActivity(intentquestion);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
                }
            });
        }

        else if(text.equals("기타 게시판")) {
            db.collection("etc_posts").add(writeinfo)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(PostActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                            Intent intentetc = new Intent(getApplicationContext(), EtcActivity.class);
                            startActivity(intentetc);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
