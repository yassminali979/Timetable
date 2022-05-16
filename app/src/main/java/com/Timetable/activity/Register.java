package com.Timetable.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Timetable.R;
import com.Timetable.database.DataHelper;
import java.util.Calendar;

public class Register extends AppCompatActivity {
    EditText email;
    EditText pass;
    EditText repass;
    EditText gender;
    EditText birthdate;
    EditText job;
    EditText name;
    Button login;
    Button register;
    DataHelper database;
    ImageView imageview1;
    EditText edittext1;
    EditText edittext2;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.username);
        name=findViewById(R.id.name);
        gender=findViewById(R.id.gender);
        birthdate=findViewById(R.id.birthdate);
        job=findViewById(R.id.job);
        pass=findViewById(R.id.password);
        repass=findViewById(R.id.repassword);
        login=findViewById(R.id.Login);
        register=findViewById(R.id.Register);
        database=new DataHelper(this);
        checkage();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class ));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String e=email.getText().toString();
                String p=pass.getText().toString();
                String rp=repass.getText().toString();
                String g=gender.getText().toString();
                String b=birthdate.getText().toString();
                String j=job.getText().toString();
                if(e.equals("")||p.equals("")||n.equals("")||g.equals("")||b.equals("")||j.equals("")||rp.equals(""))
                {
                    Toast.makeText(Register.this, "fill all the data", Toast.LENGTH_SHORT).show();
                }
                if (n.isEmpty()) {
                    name.setError("Please enter name!!");
                    return;
                }
                if (e.isEmpty()) {
                    email.setError("Please enter email!!");
                    return;
                }
                if (p.isEmpty()) {
                    pass.setError("Please enter password!!");
                    return;
                }
                if (rp.isEmpty()) {
                    repass.setError("Please enter repassword!!");
                    return;
                }
                if (g.isEmpty()) {
                    gender.setError("Please enter gender!!");
                    return;
                }
                if (b.isEmpty()) {
                    birthdate.setError("Please enter birthdate!!");
                    return;
                }
                if (j.isEmpty()) {
                    job.setError("Please enter job!!");
                    return;
                }
                else
                {
                    if(p.equals(rp))
                    {
                        Boolean check=database.checkUser(e);
                        if(check==false)
                        {
                            Boolean result=database.insert(n,e,p,g,b,j);
                            if(result==true)
                            {
                                Toast.makeText(Register.this,"Register succesfully",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(Register.this,"Register Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Human already exist Login please", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Register.this, "password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void checkage() {
        edittext1 = (EditText) findViewById(R.id.click);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        edittext2 = (EditText) findViewById(R.id.birthdate);
        button2 = (Button) findViewById(R.id.Reset);
        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(imageview1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext1.setText("");
                edittext2.setText("");
            }
        });
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        Calendar now = Calendar.getInstance();

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);
            int d = now.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, y, m, d);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            int mon = month + 1;
            Calendar birthDay = Calendar.getInstance();
            String date = day + "/" + mon + "/" + year;
            EditText edittext21 = getActivity().findViewById(R.id.click);
            EditText edittext22 = getActivity().findViewById(R.id.birthdate);
            edittext21.setText(date);
            birthDay.set(Calendar.YEAR, year);
            birthDay.set(Calendar.MONTH, month);
            birthDay.set(Calendar.DAY_OF_MONTH, day);
            double diff = (long) (now.getTimeInMillis() - birthDay.getTimeInMillis());
            if (diff < 0) {
                Toast.makeText(getContext(), "Selected date is in future.", Toast.LENGTH_SHORT).show();
                edittext22.setText("");
            } else {
                int years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
                int currMonth = now.get(Calendar.MONTH) + 1;
                int birthMonth = birthDay.get(Calendar.MONTH) + 1;
                int months = currMonth - birthMonth;
                if (months < 0) {
                    years--;
                    months = 12 - birthMonth + currMonth;
                    if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                        months--;
                } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                    years--;
                    months = 11;
                }
                int days = 0;
                if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
                    days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
                else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                    int today = now.get(Calendar.DAY_OF_MONTH);
                    months--;
                    days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
                } else {
                    days = 0;
                    if (months == 12) {
                        years++;
                        months = 0;
                    }
                }
                edittext22.setText(years + " years, " + months + " months, " + days + " days");
            }
        }
    }
}
