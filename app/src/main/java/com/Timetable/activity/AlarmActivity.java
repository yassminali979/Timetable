package com.Timetable.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Timetable.R;
import com.bumptech.glide.Glide;

public class AlarmActivity extends AppCompatActivity {
    ImageView imageView;
    TextView t;
    TextView d;
    TextView td;
    Button c;
    MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        t=findViewById(R.id.title);
        d=findViewById(R.id.description);
        td=findViewById(R.id.timeData);
        c=findViewById(R.id.done);
        imageView=findViewById(R.id.image);
        media= MediaPlayer.create(getApplicationContext(), R.raw.notification);
        media.start();
        if(getIntent().getExtras() != null) {
            t.setText(getIntent().getStringExtra("TITLE"));
            d.setText(getIntent().getStringExtra("DESC"));
            td.setText(getIntent().getStringExtra("DATE") + ", " + getIntent().getStringExtra("TIME"));
        }
        Glide.with(getApplicationContext()).load(R.drawable.alarm).into(imageView);
        c.setOnClickListener(view -> finish());

    }
}
