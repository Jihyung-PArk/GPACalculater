package com.jihyungpark.gpacalculater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class PlannerAddItemActivity extends AppCompatActivity {

    int year, month, day;
    private SharedPreferences dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner_add_item);

        dataSource = getSharedPreferences("todo items", MODE_PRIVATE);
    }

    public void plannerItemDateClicked(View view){
        final Calendar calendar = Calendar.getInstance();

        EditText plannerDate = findViewById(R.id.addPlannerItemDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        String dateFormatted = dateFormat.format(calendar.getTime());
                        plannerDate.setText(dateFormatted);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void itemAddButtonClicked(View view) {

        String subjectName = ((EditText) findViewById(R.id.addPlannerItemName)).getText().toString();
        String assignmentName = ((EditText) findViewById(R.id.addPlannerItemAssignment)).getText().toString();
        String plannerDate = ((EditText) findViewById(R.id.addPlannerItemDate)).getText().toString();



        if(subjectName.length() ==0 || assignmentName.length() ==0 || plannerDate.length() ==0){
            Toast.makeText(this, "Please fill it up.", Toast.LENGTH_LONG).show();
        } else{
            String saveText = plannerDate + "\n" + subjectName + "\n" + assignmentName;

            Set<String> items = dataSource.getStringSet("items", new HashSet<>());

            assert items != null;
            items.add(saveText);

            dataSource.edit().putStringSet("items", items).apply();

            Intent itemAdd = new Intent(this, PlannerActivity.class);
            startActivity(itemAdd);

            Toast.makeText(this, "The item is added.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<String> listSort(List<String> itemsArray) {

        String temp, stringLeft, stringRight;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");


        for(int i = 0; i < itemsArray.size(); i++){
            for (int j = i + 1; j < itemsArray.size(); j++){

                stringLeft = itemsArray.get(i).split(System.lineSeparator())[0].replace(",", "");

                LocalDate dateLeft = LocalDate.parse(stringLeft, formatter);

                stringRight = itemsArray.get(j).split(System.lineSeparator())[0].replace(",", "");
                LocalDate dateRight = LocalDate.parse(stringRight, formatter);

                if (dateLeft.compareTo(dateRight) > 0){
                    temp = itemsArray.get(i);
                    itemsArray.set(i, itemsArray.get(j));
                    itemsArray.set(j, temp);
                }
            }
        }
        return itemsArray;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "The item is cancelled.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }




}