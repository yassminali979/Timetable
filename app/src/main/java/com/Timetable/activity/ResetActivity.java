package com.Timetable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Timetable.R;
import com.Timetable.database.DataHelper;
public class ResetActivity extends AppCompatActivity {
    TextView user;
    EditText pass;
    EditText repass;
    Button c;
    DataHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        user=findViewById(R.id.r);
        pass=findViewById(R.id.password_r);
        repass=findViewById(R.id.repassword_r);
        c=findViewById(R.id.confirm);
        database=new DataHelper(this);
        Intent intent=getIntent();
        user.setText(intent.getStringExtra("UserName"));
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u=user.getText().toString();
                String p=pass.getText().toString();
                String rp=repass.getText().toString();
                if(p.equals(rp)) {
                    Boolean check = database.updatepassword(u,p);
                    if (check == true) {
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Password doesn't Updated Successfully", Toast.LENGTH_SHORT).show();

                    }
                }else
                {
                    Toast.makeText(ResetActivity.this, "Password Not matching", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}