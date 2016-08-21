package com.example.faridullah.todoapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Objects of Different Classes;
    CategoryList categoryList;

    //sorting is an object of Sorting class class which is used for the different option of sorting
    //like 1) Sort by date
    //     2) Sort by Title(Ascending)
    //     3) Sort by Title(Descending) etc;
    Sorting sorting;
    //taskArray is an array of tasks of the currently selected list;
    public static ArrayList<TaskData> tasksArray = new ArrayList();
    Button btnAdd;
    EditText enterTask;
    //ListView and Adapter for the MainActivity;
    ListView listView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setupVariables method will setup different view of the XML's files
        setupVariables();

        //Make a Default list for the first time
        if(CategoryLists.getArrayOfCategoryLists().isEmpty()) {
            categoryList.setListName("Default");
            CategoryLists.addCategoryList(categoryList);
        }
        
        //Add a new Task by going to AddTask Activity;
        btnAdd.setOnClickListener(this);

        // A method which shows a task in a list;
        showTasks();
    }

    private void setupVariables() {
        //Initialization of Class instances;
        categoryList = new CategoryList();
        sorting = new Sorting();

        btnAdd =(Button) findViewById(R.id.btnadd);
        enterTask = (EditText) findViewById(R.id.SelectTask);
        listView = (ListView) findViewById(R.id.lvTaskList);

    }

    public void showTasks() {

        if (getIntent().getExtras() != null) {
            String listName = getIntent().getExtras().getString("CategoryList");
            categoryList = CategoryLists.findList(listName);
            tasksArray = CategoryLists.taskInSpeceifedList(categoryList);

            adapter = new CustomAdapter(this, tasksArray);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else
        {
            adapter = new CustomAdapter(this, tasksArray);
            listView.setAdapter(adapter);
        }
    }

    // Create menu in the Task bar menu option;
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tasks, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        // Show Task of Current list by clicking the refresh option;
        if(id == R.id.task1){
            showTasks();
        }

        //Go to the List Activity by clicking the List option;
        else if(id == R.id.task2){
            Intent listActivity = new Intent(MainActivity.this, Lists.class);
            startActivity(listActivity);
        }

        else if(id == R.id.task3){
           Intent completedTaskActivity = new Intent(MainActivity.this, CompletedTasks.class);
            startActivity(completedTaskActivity);
        }

        else if(id == R.id.task4){
            String sorting_options[] = {"Due date[Most recent]", "Priority(Highest" , "Priority(lowest", "Ascending[A-Z]", "Descending[Z-A]"};
            final AlertDialog.Builder sortDialog = new AlertDialog.Builder(this);
            sortDialog.setTitle(" Select Sorting option");
            sortDialog.setItems(sorting_options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            sorting.sort_A_Z(tasksArray);
                            break;
                        case 4:
                            sorting.sort_Z_A(tasksArray);
                            break;
                    }
                    adapter.notifyDataSetChanged();

                }
            });
            sortDialog.setCancelable(false);
            sortDialog.setPositiveButton("Ok", null);
            sortDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            sortDialog.create();
            sortDialog.show();
        }

        //Go to search activity by clicking the search option;
        else  if(id == R.id.task5){
            Intent searchActivity = new Intent(MainActivity.this, Search.class);
            startActivity(searchActivity);
        }

        // Go to delete Activity by clicking the delete option;
        else if(id == R.id.task6){
            Intent deleteActivity = new Intent(MainActivity.this, Delete.class);
            startActivity(deleteActivity);
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Intent gotoAddTask = new Intent(MainActivity.this, AddTask.class);
        gotoAddTask.putExtra("text", enterTask.getText().toString());
        enterTask.setText("");
        startActivity(gotoAddTask);
    }

}// end of MainActivity;

