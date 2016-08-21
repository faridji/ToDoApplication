package com.example.faridullah.todoapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Farid ullah on 7/28/2016.
 */
public class CustomAdapter extends ArrayAdapter {


    Context context;

    public CustomAdapter(Context context, ArrayList list){
        super(context,0,list);
        //This will set the context of the class to the sent context;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final TaskData task = (TaskData) getItem(position);
        final DateTime date = task.getDueDateTime();
        String title = task.getTitle().toString();

        final String dateTime = String.format("%d/%d/%d", date.getMonth(),date.getDay(), date.getYear());

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.customrow,parent,false);
        }

        final CheckBox checkTask = (CheckBox) convertView.findViewById(R.id.chcboxTask);
        final TextView taskTitle = (TextView) convertView.findViewById(R.id.tvTaskTitle);
        final TextView taskIsComplete = (TextView) convertView.findViewById(R.id.tvComplete);
        final TextView dateView = (TextView) convertView.findViewById(R.id.tvDate);

        checkTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkTask.isChecked()){
                    taskIsComplete.setText("Completed");
                    task.setComplete(true);
                    task.addTaskToCompletedList(task);
                    taskIsComplete.setTextColor(Color.GREEN);
                }
                else {
                    taskIsComplete.setText("Uncompleted");
                    task.setComplete(false);
                    task.removeTaskFromCompletedList(task);
                    taskIsComplete.setTextColor(Color.BLACK);
                }
            }
        });


        taskTitle.setText(title);

        //Following if condition will determine whether this task is completed or not;
        if(task.isComplete()){
            checkTask.setChecked(true);
            taskIsComplete.setText("Completed");
        }
        else {
            checkTask.setChecked(false);
            Log.d("Farid:" , "Tank");
            taskIsComplete.setText("unCompleted");
        }


        dateView.setText(dateTime + ",  ");
        return convertView;
    }
}
