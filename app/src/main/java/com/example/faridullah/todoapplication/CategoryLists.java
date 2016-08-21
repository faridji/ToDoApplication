package com.example.faridullah.todoapplication;


import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Farid ullah on 6/18/2016.
 */
public class CategoryLists {
    private static ArrayList<CategoryList> arrayOfCategoryLists = new ArrayList<CategoryList>();
    private static CategoryList categoryList = new CategoryList();
    public static void addCategoryList(CategoryList categoryList){
        arrayOfCategoryLists.add(categoryList);
    }

    public static void deleteCategoryList(CategoryList categoryList){
        arrayOfCategoryLists.remove(categoryList);
    }

    public static ArrayList<CategoryList> getArrayOfCategoryLists() {
        return arrayOfCategoryLists;
    }

    public static CategoryList findList(String name){
        for(CategoryList list : arrayOfCategoryLists){
            if(list.getListName().equals(name))
                return list;
        }
        return null;
    }

    public static TaskData findTask(String name){

        for (int i=0;i<CategoryLists.getArrayOfCategoryLists().size();i++) {
            for (int j = 0; j < CategoryLists.getArrayOfCategoryLists().get(i).countTasks();j++) {
                if(CategoryLists.getArrayOfCategoryLists().get(i).getTask(j).getTitle().equals(name)){
                    String nameOfList = CategoryLists.getArrayOfCategoryLists().get(i).getListName();
                    CategoryList list = findList(nameOfList);
                    TaskData data = CategoryLists.getArrayOfCategoryLists().get(i).getTask(j);
                    return data;
                }
            }
        }
        return null;
    }

    public static void removeTask(String name){

        for (int i=0;i<CategoryLists.getArrayOfCategoryLists().size();i++) {
            for (int j = 0; j < CategoryLists.getArrayOfCategoryLists().get(i).countTasks();j++) {
                if(CategoryLists.getArrayOfCategoryLists().get(i).getTask(j).getTitle().equals(name)){

                    String nameOfList = CategoryLists.getArrayOfCategoryLists().get(i).getListName();
                    CategoryList list = findList(nameOfList);
                    TaskData data = CategoryLists.getArrayOfCategoryLists().get(i).getTask(j);
                    list.deleteTask(data);
                }
            }
        }
    }

    public static int findListIndex(String listName){
        for (CategoryList c:arrayOfCategoryLists) {
            if (c.toString().trim().toUpperCase().equals(listName.trim().toUpperCase())) {
                return arrayOfCategoryLists.indexOf(c);
           }
        }
        return -1;
    }

    public static ArrayList<TaskData> taskInSpeceifedList(CategoryList categoryList){
        ArrayList<TaskData> tasksArrayList=new ArrayList<TaskData>();
        for(int i=0; i<categoryList.countTasks(); i++){
            tasksArrayList.add(categoryList.getTask(i));
        }
        return tasksArrayList;
    }


    public static ArrayList<TaskData> tasksArrayList(){
        ArrayList<TaskData> tasksArrayList=new ArrayList<TaskData>();
        for (int i=0;i<CategoryLists.getArrayOfCategoryLists().size();i++) {
            for (int j = 0; j < CategoryLists.getArrayOfCategoryLists().get(i).countTasks();j++) {
                tasksArrayList.add(CategoryLists.getArrayOfCategoryLists().get(i).getTask(j));
            }
        }
        return tasksArrayList;
    }

    public static void renameList(String oldName, String newName){
        CategoryLists.getArrayOfCategoryLists().get(CategoryLists.findListIndex(oldName.trim())).setListName(newName);
    }

    public String toString(){
        return categoryList.toString();
    }
}

