package com.example.faridullah.todoapplication;

import java.util.ArrayList;

/**
 * Created by Farid ullah on 6/17/2016.
 */
public class TaskData {
    private String title;
    private String description;
    private DateTime dueDateTime;
    private DateTime remindingDateTime;
    private ArrayList listForTask;
    private String proirity;
    private static ArrayList<TaskData> completedTasks = new ArrayList<>();
    private boolean isComplete = false;


    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getDueDateTime() {
        return dueDateTime;
    }

    public DateTime getRemindingDate() {
        return remindingDateTime;
    }

    public String getProirity() {
        return proirity;
    }

    public ArrayList getListForTask() {
        return listForTask;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDateTime(DateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public void setListForTask(ArrayList listForTask) {
        this.listForTask = listForTask;
    }

    public void setProirity(String proirity) {
        this.proirity = proirity;
    }

    public void setRemindingDateTime(DateTime remindingDateTime) {
        this.remindingDateTime = remindingDateTime;
    }

    public void addTaskToCompletedList(TaskData taskData){
        completedTasks.add(taskData);
    }

    public void removeTaskFromCompletedList(TaskData taskData){
        completedTasks.remove(taskData);
    }
    public ArrayList<TaskData> getCompletedTasks() {
        return completedTasks;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}

