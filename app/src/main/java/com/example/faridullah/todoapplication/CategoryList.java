package com.example.faridullah.todoapplication;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Farid ullah on 6/17/2016.
 */
public class CategoryList {

    private ArrayList<TaskData> categoryList;
    private String listName;

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    public CategoryList(){
        categoryList = new ArrayList<TaskData>();
    }

    public void addTask(TaskData task){
        this.categoryList.add(task);
    }

    public TaskData getTask(int i){
        return categoryList.get(i);
    }

    public void deleteTask(TaskData task){
        categoryList.remove(task);
    }

    public int countTasks(){
        return categoryList.size();
    }

    public String toString(){
        return getListName();
    }

}
