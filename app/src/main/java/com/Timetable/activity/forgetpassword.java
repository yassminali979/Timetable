package com.Timetable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Timetable.R;
import com.Timetable.database.DataHelper;
public class forgetpassword extends AppCompatActivity {
    EditText user;
    Button reset;
    DataHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        user=findViewById(R.id.enter_email);
        reset=findViewById(R.id.reset);
        database=new DataHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u=user.getText().toString();
                Boolean check=database.checkUser(u);
                if(check==true)
                {
                    Intent intent=new Intent(getApplicationContext(),ResetActivity.class);
                    intent.putExtra("UserName",u);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(forgetpassword.this, "User Doesn't exists??!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}