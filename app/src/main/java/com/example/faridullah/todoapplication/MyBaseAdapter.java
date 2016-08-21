package com.example.faridullah.todoapplication;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.faridullah.todoapplication.R;

import java.util.ArrayList;

public class MyBaseAdapter extends ArrayAdapter{

    private final Activity context;
    private final ArrayList<TaskData> listOfTasks;
    private final Integer imageId;
    String task;
    TaskData taskdata;
    CategoryList categorylist;
    Delete deleteActivity;

    public MyBaseAdapter(Activity context, ArrayList list, Integer imageId) {

        super(context, R.layout.list_view_row, list);
        this.context = context;
        this.listOfTasks = list ;
        this.imageId = imageId;
        this.taskdata = new TaskData();
        this.categorylist = new CategoryList();
        this.deleteActivity = new Delete();
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();
        view = inflater.inflate(R.layout.list_view_row, null, true);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.chkTask);
        final ImageButton imageButton = (ImageButton) view.findViewById(R.id.btnDelete);
        task = listOfTasks.get(position).toString();
        checkBox.setText(task);
        imageButton.setImageResource(imageId);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked() == true) {

                    String deletingList = checkBox.getText().toString();
                    CategoryLists.removeTask(deletingList);
                    notifyDataSetChanged();
                }
            }

        });
         return view;
    }


}