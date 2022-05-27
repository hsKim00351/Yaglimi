package com.example.yaglimi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.yaglimi.views.Memberinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    private EditText edit_name, edit_email, edit_pw, edit_phone, edit_pwcheck, edit_birth;
    private Button register_btn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.my_toolbar_register);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("회원가입");

        firebaseAuth =  FirebaseAuth.getInstance();
        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_pw = findViewById(R.id.edit_pw);
        edit_pwcheck = findViewById(R.id.edit_pwcheck);
        edit_phone = findViewById(R.id.edit_phone);
        edit_birth = findViewById(R.id.edit_birth);
        register_btn = findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                final String email = edit_email.getText().toString().trim();
                final String pwd = edit_pw.getText().toString().trim();
                final String pwdcheck = edit_pwcheck.getText().toString().trim();

                if(pwd.equals(pwdcheck)) {
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
                                                    Intent intentlogin = new Intent(getApplicationContext(), LoginActivity.class);
                                                    startActivity(intentlogin);
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
                }
                //비밀번호와 비밀번호 확인이 다를경우
                else if(pwd != pwdcheck) {
                    Toast.makeText(RegisterActivity.this, "비밀번호가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}
