package com.example.faridullah.todoapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.faridullah.todoapplication.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Farid ullah on 5/1/2016.
 */
public class Search extends Activity implements View.OnClickListener{

    EditText searchQuery;
    ArrayAdapter adapter;
    ListView listView;
    private boolean isFound;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        setUpVariables();
    }

    private void setUpVariables() {
        isFound = false;
        Button back = (Button) findViewById(R.id.backbtn);
        Button search = (Button)findViewById(R.id.btnSearch);
        listView = (ListView) findViewById(R.id.lvSearchedTasks);
        searchQuery = (EditText) findViewById(R.id.etSearch);

        back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    public void onClick(View v) {
        //Go to MainActivity;
        if(v.getId() == R.id.backbtn){
            Intent back = new Intent(Search.this, MainActivity.class);
            startActivity(back);
        }

        //Search a Task in the list;
        if(v.getId() == R.id.btnSearch){
            ArrayList<String> tasksList = new ArrayList<String>();
            for(int i=0; i<CategoryLists.tasksArrayList().size(); i++){

                if(CategoryLists.tasksArrayList().get(i).toString().equals(searchQuery.getText().toString())){
                    isFound = true;
                    tasksList.add(CategoryLists.tasksArrayList().get(i).toString());
                    //adapter = new ArrayAdapter(this, R.layout.list_items , tasksList);
                    listView.setAdapter(adapter);
                }
            }
            if (isFound == false){
                Toast.makeText(Search.this, "Sorry, Task does't found", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
