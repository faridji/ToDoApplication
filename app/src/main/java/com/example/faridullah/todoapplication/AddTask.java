package com.example.faridullah.todoapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Farid ullah on 4/30/2016.
 */
public class AddTask extends Activity implements View.OnClickListener{

    EditText titleField,descriptionField;
    Button proirityList,categoryList,remind_date,remind_time,clear_remind,dueDate,dueTime,clearDate,clearTime,save,back,cancel;
    DateTime dueDateTime;
    DateTime remindDateTime;
    int year,month,day,minute,hour;
    TaskData taskData;
    Intent intent;
    PendingIntent pendingIntent;
    public static int pendingIntentCode;
    AlarmManager alarmManager;
    Calendar calender;
    int format;

    public static CategoryList newList;
    private String[] proirities = {"Normal", "Highest", "Lowest"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask);
        setupVariables();

        //Set The text of the Title text field to the Task name send by the MainScreen Activity
        titleField.setText(getIntent().getExtras().getString("text"));
    }

    @Override
    public void onClick(View v) {

        //Go back to Main Activity;
        if(v.getId() == R.id.btnback){
            Intent back = new Intent(AddTask.this, MainActivity.class);
            startActivity(back);
            finish();
        }
        // Select a list from available lists in an alert Dialog box;
        else if(v.getId() == R.id.btnCatogery){

            final String[] categoryListsnames = new String[CategoryLists.getArrayOfCategoryLists().size()];
            for (int i=0; i<CategoryLists.getArrayOfCategoryLists().size(); i++){
                categoryListsnames[i] = CategoryLists.getArrayOfCategoryLists().get(i).toString();
            }
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Choose Category List");
            dialog.setCancelable(false);
            dialog.setItems(categoryListsnames, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    categoryList.setText(categoryListsnames[which]);
                }
            });
            dialog.setPositiveButton("Ok", null);
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog.create();
            dialog.show();
        }


        //Add a Task into a List;
        else if(v.getId() == R.id.btnSave){

            String listName = categoryList.getText().toString();
            String taskName = titleField.getText().toString();
            newList = CategoryLists.findList(listName);
            taskData.setTitle(taskName);

            taskData.setDescription(descriptionField.getText().toString());
            taskData.setDueDateTime(dueDateTime);
            taskData.setRemindingDateTime(remindDateTime);
            newList.addTask(taskData);
            setAlarm();
            intent = new Intent(AddTask.this, MainActivity.class);
            intent.putExtra("CategoryList", listName);
            startActivity(intent);

        }

        //Go back to main Activity after canceling the Add task form;
        else if(v.getId() == R.id.btnCancel){
            Intent BackToMainActivity = new Intent(AddTask.this,MainActivity.class);
            startActivity(BackToMainActivity);
        }

        //Remove date from set Date button;
        else if(v.getId() == R.id.btnDate){
            if(dueDateTime == null){
                dueDateTime = new DateTime();
            }
            else
                setDate(dueDate, dueDateTime);
        }

        else if(v.getId() == R.id.btnTime){
            if(dueDateTime == null){
                dueDateTime = new DateTime();
            }
            setTime(dueTime, dueDateTime);

        }

        else if (v.getId() == R.id.btnRemindingDate) {
            if(remindDateTime == null){
                remindDateTime = new DateTime();
            }
            else
                setDate(remind_date, remindDateTime);

        }

        else if(v.getId() == R.id.btnRemindingTime) {
            if (remindDateTime == null) {
                remindDateTime = new DateTime();

            } else {

                setTime(remind_time, remindDateTime);

            }
        }


        //Remove time from set time button;
        else if(v.getId() == R.id.btnRemoveDate){
            dueDateTime = null;
            dueDate.setText("Set Date");
        }
        //Remove time from set time button;
        else if(v.getId() == R.id.btnRemoveTime){
            dueDateTime = null;
            dueTime.setText("Set Time");
        }
        //Remove time from set time button;
        else if(v.getId() == R.id.btnRemoveClear){
            remindDateTime = null;
            remind_date.setText("Set Time");
            remind_time.setText("Set Time");
        }


        //A popup menu to show a list of priority;
        else if(v.getId() == R.id.btnPriority) {

            AlertDialog.Builder priorityDialog = new AlertDialog.Builder(this);
            priorityDialog.setTitle("Select Priority");
            priorityDialog.setCancelable(false);
            priorityDialog.setItems(proirities, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    taskData.setProirity(proirities[which]);
                    proirityList.setText(proirities[which]);
                }
            });
            priorityDialog.setPositiveButton("Ok", null);
            priorityDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            priorityDialog.show();

        }
    }

    private void setAlarm() {

        Log.d("Alarm Method:" ,"I am here in Alarm method");
        calender = Calendar.getInstance();
        calender.set(Calendar.DAY_OF_MONTH, remindDateTime.getDay());
        calender.set(Calendar.MONTH, remindDateTime.getMonth());
        calender.set(Calendar.YEAR, remindDateTime.getYear());
        calender.set(Calendar.MINUTE, remindDateTime.getMinute());
        calender.set(Calendar.HOUR, remindDateTime.getHour());
        calender.set(Calendar.AM_PM, remindDateTime.getFormat());

        String dateTime = String.format("%d/%d/%d %d:%d", remindDateTime.getDay(), remindDateTime.getMonth(), remindDateTime.getYear(),remindDateTime.getHour(),remindDateTime.getMinute());

        //Toast.makeText(AddTask.this, taskData.getTitle(), Toast.LENGTH_SHORT).show();

        intent = new Intent(AddTask.this, AlarmReceiver.class);
        intent.putExtra("TASK_TITLE",taskData.getTitle());
        pendingIntent = PendingIntent.getBroadcast(AddTask.this,pendingIntentCode,intent,0);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pendingIntent);
        Log.d("Alarm Method:" ," Alarm is set");
        Toast.makeText(AddTask.this, "Alarm has been set at " + dateTime, Toast.LENGTH_SHORT).show();
    }

    public void setDate(final Button btn, final DateTime dateTime){

        calender = Calendar.getInstance();
        year = calender.get(Calendar.YEAR);
        month=calender.get(Calendar.MONTH);
        day=calender.get(Calendar.DAY_OF_MONTH);


        dateTime.setYear(year);
        dateTime.setDay(day);
        dateTime.setMonth(month);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(AddTask.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // Display Selected date in btnDueDate
                        dateTime.setDate(day, (month+1) , year);

                        if(dateTime.getYear() < calender.get(Calendar.YEAR) || dateTime.getMonth() < calender.get(Calendar.MONTH ) + 1||
                                dateTime.getDay() < calender.get(Calendar.DAY_OF_MONTH) ) {

                            Toast.makeText(AddTask.this, "Invalid date is selected, try again", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        btn.setText(day + "-" + (month + 1) + "-" + year);

                        //Toast.makeText(AddTask.this, remindDateTime.getYear(), Toast.LENGTH_SHORT).show();
                    }

                }
                , dateTime.getYear(),dateTime.getMonth(),dateTime.getDay());
        dpd.show();

    }

    public void setTime(final Button btn, final DateTime dateTime){
        // Process to get Current Time
        calender = Calendar.getInstance();
        hour=calender.get(Calendar.HOUR_OF_DAY);
        minute=calender.get(Calendar.MINUTE);


        dateTime.setMinutes(minute);
        dateTime.setHour(hour);
        dateTime.setFormat(format);
        
        // Launch Time Picker Dialog
        TimePickerDialog tpd = new TimePickerDialog(AddTask.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String formatAMPM;
                        hour=hourOfDay;
                        if (hour == 0) {
                            hour += 12;
                            formatAMPM = "AM";
                            format = 0;
                        }
                        else if (hour == 12) {
                            formatAMPM = "PM";
                            format = 1;
                        } else if (hour > 12) {
                            hour -= 12;
                            formatAMPM= "PM";
                            format = 1;
                        } else {
                            formatAMPM = "AM";
                            format = 0;
                        }

                        dateTime.setTime(hourOfDay,minute,format);
                        if(dateTime.getHour() < calender.get(Calendar.HOUR)  || dateTime.getMinute() < calender.get(Calendar.MINUTE) || dateTime.getFormat() != calender.get(Calendar.AM_PM) ){
                            Toast.makeText(AddTask.this, "Invalid time is selected, try again", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        btn.setText(hour + ":" + minute+"  "+ formatAMPM);
                    }
                },dateTime.getHour(),dateTime.getMinute() , false);
        tpd.show();
    }

    private void setupVariables() {

        dueDateTime = new DateTime();
        taskData = new TaskData();
        remindDateTime = new DateTime();
        titleField = (EditText) findViewById(R.id.etTitle);
        descriptionField = (EditText) findViewById(R.id.etDesc);
        save = (Button) findViewById(R.id.btnSave);
        cancel = (Button) findViewById(R.id.btnCancel);
        back = (Button) findViewById(R.id.btnback);
        dueDate = (Button) findViewById(R.id.btnDate);
        dueTime = (Button) findViewById(R.id.btnTime);
        clearDate = (Button) findViewById(R.id.btnRemoveDate);
        clearTime = (Button) findViewById(R.id.btnRemoveTime);
        proirityList = (Button) findViewById(R.id.btnPriority);
        categoryList = (Button) findViewById(R.id.btnCatogery);
        remind_date = (Button) findViewById(R.id.btnRemindingDate);
        remind_time = (Button) findViewById(R.id.btnRemindingTime);
        clear_remind = (Button) findViewById(R.id.btnRemoveClear);
        dueDate.setOnClickListener(this);
        dueTime.setOnClickListener(this);
        remind_date.setOnClickListener(this);
        remind_time.setOnClickListener(this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        clearDate.setOnClickListener(this);
        clearTime.setOnClickListener(this);
        proirityList.setOnClickListener(this);
        categoryList.setOnClickListener(this);
        clear_remind.setOnClickListener(this);
    }


} // end of Activity AddTask