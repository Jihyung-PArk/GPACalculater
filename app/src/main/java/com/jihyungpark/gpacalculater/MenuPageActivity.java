package com.jihyungpark.gpacalculater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
    }

    //open GPA Calculator activity
    public void gpaCalculatorClicked(View view){
        Intent gpaCalculator = new Intent(this, GPACalculatorActivity.class);
        startActivity(gpaCalculator);
    }

    //open Subject Calculator activity
    public void subjectCalculatorClicked(View view){
        Intent subjectCalculator = new Intent(this, SubjectCalculatorActivity.class);
        startActivity(subjectCalculator);
    }

    //open Planner activity
    public void plannerClicked(View view){
        Intent planner = new Intent(this, PlannerActivity.class);
        startActivity(planner);
    }
}