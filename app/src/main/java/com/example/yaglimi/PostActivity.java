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

import com.example.yaglimi.views.Memberinfo;
import com.example.yaglimi.views.Writeinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostActivity extends AppCompatActivity {
    private TextView post_activity;
    private EditText post_title, post_content;
    private Button post_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post_activity = findViewById(R.id.post_activity);
        post_title = findViewById(R.id.post_title);
        post_content = findViewById(R.id.post_content);
        post_confirm = findViewById(R.id.post_confirm);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        post_activity.setText(text);

        post_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /*private void userInfo() {
        final String title = post_title.getText().toString();
        final String content = post_content.getText().toString();

        if(title.length() > 0 && content.length()>0) {
            Writeinfo wi = new Writeinfo(title, content);
        }
            //신규계정 등록
            firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //가입 성공
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                        String email = user.getEmail();
                        String uid = user.getUid();
                        String name = edit_name.getText().toString().trim();
                        String phone = edit_phone.getText().toString().trim();
                        String birth = edit_birth.getText().toString().trim();

                        Memberinfo member = new Memberinfo(name, phone, birth);

                        if (user != null) {
                            db.collection("users").document(user.getUid()).set(member)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void avoid) {
                                            Toast.makeText(RegisterActivity.this, "회원등록 성공", Toast.LENGTH_SHORT).show();
                                            Intent intentmain = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intentmain);
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "회원등록 실패", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }
            });
    }*/
}
