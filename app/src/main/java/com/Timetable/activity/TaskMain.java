package com.Timetable.activity;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Timetable.R;
import com.Timetable.Task;
import com.Timetable.adapter.TaskAdapter;
import com.Timetable.bottomfragment.taskfragment;
import com.Timetable.database.Database;
import com.Timetable.reciveralarm.AlarmReceiver;
import com.bumptech.glide.Glide;



import java.util.ArrayList;
import java.util.List;
public class TaskMain extends AppCompatActivity implements taskfragment.setRefreshListener {
    RecyclerView Recycle;
    TextView add;
    TaskAdapter taskAdapter;
    List<Task> tasks = new ArrayList<>();
    ImageView n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskmain);
        Recycle=findViewById(R.id.r);
        add=findViewById(R.id.addTask);
        n=findViewById(R.id.d);
        setAdapter();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ComponentName receiver = new ComponentName(this, AlarmReceiver.class);
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        Glide.with(getApplicationContext()).load(R.drawable.taskk).into(n);

        add.setOnClickListener(view -> {
            taskfragment taskfragment = new taskfragment();
            taskfragment.setTaskId(0, false, this, TaskMain.this);
            taskfragment.show(getSupportFragmentManager(), taskfragment.getTag());
        });
        getSavedTasks();
    }

    public void setAdapter() {
        taskAdapter = new TaskAdapter(this, tasks, this);
        Recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Recycle.setAdapter(taskAdapter);
    }

    private void getSavedTasks() {

        class GetSavedTasks extends AsyncTask<Void, Void, List<Task>> {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                tasks = Database.getInstance(getApplicationContext()).getDatabaseHelper().databaseAction().getAllTasksList();
                return tasks;
            }
            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                n.setVisibility(tasks.isEmpty() ? View.VISIBLE : View.GONE);
                setAdapter();
            }
        }
        GetSavedTasks savedTasks = new GetSavedTasks();
        savedTasks.execute();
    }

    @Override
    public void refresh() {
        getSavedTasks();
    }
}
