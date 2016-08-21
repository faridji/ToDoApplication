package com.example.faridullah.todoapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.faridullah.todoapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farid ullah on 4/30/2016.
 */
public class Lists extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private CategoryList specifiedList;
    public static int posOfList;
    private ArrayAdapter<CategoryList> adapter;
    ListView listView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists);
        setupVariables();

        //Load all lists in the ArrayOfCategoryList;
        adapter = new ArrayAdapter<CategoryList>(this, R.layout.newlist, CategoryLists.getArrayOfCategoryLists());
        listView = (ListView) findViewById(R.id.lvLists);
        listView.setAdapter(adapter);

        //Hold Click implementation
        listView.setOnItemLongClickListener(this);

        //Short Click implementation
        listView.setOnItemClickListener(this);


    }

    private void setupVariables() {
        //Declare UI Controlls
        Button addList = (Button) findViewById(R.id.btnAddList);
        Button back = (Button) findViewById(R.id.btnback);

        // click Listener for adding a new List and back button;
        addList.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    DialogInterface.OnClickListener actionListener = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {

            final EditText newName = new EditText(Lists.this);
            switch (which){

                case 0:
                    dialog.cancel();
                    AlertDialog.Builder renameDailog = new AlertDialog.Builder(Lists.this);
                    renameDailog.setCancelable(false);
                    renameDailog.setTitle("Rename List");

                    newName.setHint("Enter new name");
                    renameDailog.setView(newName);
                    renameDailog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = newName.getText().toString();
                            CategoryLists.renameList(listView.getAdapter().getItem(posOfList).toString(),name);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "List renamed", Toast.LENGTH_SHORT).show();
                        }
                    });

                    renameDailog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    renameDailog.show();
                    break;


                case 1:
                    final AlertDialog.Builder dialogForDelete = new AlertDialog.Builder(Lists.this);
                    dialogForDelete.setTitle("Delete");
                    dialogForDelete.setIcon(R.drawable.warningicon);
                    dialogForDelete.setCancelable(false);
                    dialogForDelete.setMessage("All tasks in this list will be deleted");
                    dialogForDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    dialogForDelete.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CategoryList categoryList = CategoryLists.findList(listView.getAdapter().getItem(posOfList).toString());

                            if(categoryList.getListName().equals("Default")){
                                Toast.makeText(Lists.this, "You can't delete Default List", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            CategoryLists.deleteCategoryList(categoryList);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    dialogForDelete.show();
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnAddList){

            //Create an alertDialog that will asks for entering a new list
            //It have two buttons(Ok,Cancel) and edit text that will prompt for a new List name;

            specifiedList = new CategoryList();
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("New List");
            final EditText enterListName = new EditText(this);
            enterListName.setHint("Enter List Name:");
            enterListName.setPadding(15,60,15,40);
            dialog.setCancelable(false);

            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String newList = enterListName.getText().toString();
                    specifiedList.setListName(newList);
                    CategoryLists.addCategoryList(specifiedList);
                    adapter.notifyDataSetChanged();
                    dialog.cancel();
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            dialog.setView(enterListName);
            dialog.show();
            //End of AlertDialog;
        }

        //Go back to MainActivity;
        if (v.getId() == R.id.btnback)
        {
            Intent intent = new Intent(Lists.this,MainActivity.class);
            startActivity(intent);
            finish();   // it will remove this activity from the Stack;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = listView.getAdapter().getItem(position).toString();
        specifiedList = CategoryLists.findList(name);
        String[] tasks = new String[specifiedList.countTasks()];

        AlertDialog.Builder tasksDialog = new AlertDialog.Builder(Lists.this);
        tasksDialog.setTitle(specifiedList.toString());
        if(specifiedList.countTasks() == 0){
            tasksDialog.setMessage("No Tasks added yet");
        }
        else {
            for (int i = 0; i < specifiedList.countTasks(); i++) {
                tasks[i] = specifiedList.getTask(i).toString();
            }

            tasksDialog.setItems(tasks, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        tasksDialog.setPositiveButton("Ok", null);
        tasksDialog.setNegativeButton("Cancel",null);
        tasksDialog.create();
        tasksDialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        String[] options = {"Rename", "Delete"};
        posOfList = position;
        dialog.setCancelable(false);
        dialog.setTitle("Choose an Option");
        dialog.setItems(options, actionListener);
        dialog.setPositiveButton("Ok", null);
        dialog.setNegativeButton("Cancel", null);
        dialog.show();
        return true;
    }
}