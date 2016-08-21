package com.example.faridullah.todoapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by Farid ullah on 7/28/2016.
 */
public class CompletedTasks extends Activity {

    TaskData taskData;
    TextView showResults;
    ListView completedTaskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completedtasks);
        setupVariables();

        if(taskData.getCompletedTasks().isEmpty()){
            showResults.setText("No Completed Tasks yet!");
        }

        CustomAdapter adapter = new CustomAdapter(this,taskData.getCompletedTasks());
        completedTaskListView.setAdapter(adapter);
    }

    private void setupVariables() {
        taskData = new TaskData();
        showResults = (TextView) findViewById(R.id.tvShowCompletedTasks);
        completedTaskListView = (ListView) findViewById(R.id.lvCompletedTasks);
    }
}
