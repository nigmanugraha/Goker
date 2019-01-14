package com.android.nigma.goker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nigma.goker.Model.MemberModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    TextView inputEmail,inputPassword,inputNama;
    Button btnRegister;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputNama = findViewById(R.id.inputNama);
        progressBar = findViewById(R.id.progressBar);
    }

    public void btnRegister(View view){
        final String name = inputNama.getText().toString();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        if(name.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Enter email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Enter email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 8){
            Toast.makeText(RegisterActivity.this, "Password too short, enter minimum 8 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registration Failed: " + task.getException(),Toast.LENGTH_LONG);
                            return;
                        }
                        createMember(task.getResult().getUser().getUid(),name);
                    }
                });
    }

    private void createMember(String uid, String name){
        MemberModel member = new MemberModel(name);

        db.collection("member").document(uid)
                .set(member.toMap())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            return;
                        }
                        Toast.makeText(RegisterActivity.this,"Registration Complete", Toast.LENGTH_LONG);
                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        finish();
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
