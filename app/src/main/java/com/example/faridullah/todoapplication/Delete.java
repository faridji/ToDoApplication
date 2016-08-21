package com.example.faridullah.todoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faridullah.todoapplication.R;

import java.util.ArrayList;

/**
 * Created by Farid ullah on 4/30/2016.
 */

public class Delete extends AppCompatActivity {

    ListView listView;
    Integer imageId;
    ArrayList<TaskData> tasksArray;

    public void onCreate(Bundle savedInstanceSate){
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.delete);

        //Set name of the Taskbar;
        setTitle("Delete Tasks");

        listView = (ListView) findViewById(R.id.lvDelete);
        imageId = R.drawable.btndelete;

        tasksArray = CategoryLists.tasksArrayList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView) view).getText().toString();
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
            }
        });

        registerForContextMenu(listView);
        showList();
    }

    public void showList(){
        MyBaseAdapter adapter = new MyBaseAdapter(Delete.this,tasksArray,R.drawable.btndelete);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tasks, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        if(id == R.id.task1){
            showList();
        }

        else if(id == R.id.task2){
            Intent listActivity = new Intent(Delete.this, Lists.class);
            startActivity(listActivity);
        }
        else if(id == R.id.task3){
            System.out.println("Do nothing");
        }
        else if(id == R.id.task4){
            System.out.println("Do nothing");
        }
        else  if(id == R.id.task5){
            Intent searchActivity = new Intent(Delete.this, Search.class);
            startActivity(searchActivity);
        }
        return true;
    }

}
