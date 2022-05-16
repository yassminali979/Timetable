package com.Timetable.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Timetable.R;
import com.Timetable.database.DataHelper;

public class Login extends AppCompatActivity {
    Button login;
    TextView r;
    Button register;
    EditText user;
    EditText pass;
    TextView forget;
    DataHelper database;
    CheckBox Remember;
    SharedPreferences shared;
    SharedPreferences.Editor edit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login_id);
        register=findViewById(R.id.reg);
        user=findViewById(R.id.U);
        pass=findViewById(R.id.Pass);
        forget=findViewById(R.id.forget);
        Remember=findViewById(R.id.remeber);
        shared = getSharedPreferences("info",MODE_PRIVATE);
        edit = shared.edit();
        database=new DataHelper(this);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,forgetpassword.class ));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class ));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if (u.equals("") || p.equals("")) {
                    Toast.makeText(Login.this, "Please fill all data", Toast.LENGTH_SHORT).show();
                }
                if (u.isEmpty()) {
                    user.setError("Please enter email!!");
                    return;
                }
                if (p.isEmpty()) {
                    pass.setError("Please enter password!!");
                    return;
                }
                boolean check = database.CheckUser(u, p);
                if (check == true) {
                    if (Remember.isChecked()) {
                        edit.putString("username", u);
                        edit.putString("password", p);
                        edit.apply();
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, TaskMain.class);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(Login.this, "Invalid Data ?!Please Register.....", Toast.LENGTH_SHORT).show();
                }
                Boolean r=database.CheckUser(u,p);
                if(r==true)
                {
                    Toast.makeText(Login.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,TaskMain.class ));
                }
                else {
                    Toast.makeText(Login.this, "Invalid Data ?!Please Register.....", Toast.LENGTH_SHORT).show();
                }

            }
        });
        getdata();
    }
    public  void getdata()
    {
        shared = getSharedPreferences("info",MODE_PRIVATE);
        String name =shared.getString("username","");
        user.setText(name);
        String password =shared.getString("password","");
        pass.setText(password);
    }

}