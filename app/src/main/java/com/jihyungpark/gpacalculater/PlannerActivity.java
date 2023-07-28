package com.jihyungpark.gpacalculater;

import static com.jihyungpark.gpacalculater.R.anim.blink;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PlannerActivity extends AppCompatActivity {

    public ArrayList<Integer> overdue = new ArrayList<Integer>();
    public ArrayList<Integer> closedDue = new ArrayList<Integer>();
    private ListView assignmentList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences dataSource;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

//        Animation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(500); //You can manage the blinking time with this parameter
//        anim.setStartOffset(20);
//        anim.setRepeatMode(Animation.REVERSE);
//        anim.setRepeatCount(Animation.INFINITE);

        assignmentList = findViewById(R.id.assignmentList);

        assignmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView itemView = (TextView) view;

                adapter.remove(itemView.getText().toString());
                if (overdue.contains(position)) {
                    overdue.remove(Integer.valueOf(position));
                } else if (closedDue.contains(position)) {
                    closedDue.remove(Integer.valueOf(position));
                }
                recreate();
            }
        });

        adapter = new ArrayAdapter<String>(this, R.layout.item){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                if(overdue.contains(position)){
                    view.setBackgroundColor(Color.parseColor("#4de06666"));
                } else if (closedDue.contains(position)) {
                    view.setBackgroundColor(Color.parseColor("#4dffd966"));
                }else{
                    view.setBackgroundColor(Color.parseColor("#4d0072BC"));
                }

                    return view;

            }
        };

        assignmentList.setAdapter(adapter);


        dataSource = getSharedPreferences("todo items", MODE_PRIVATE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();

        Set<String> newItems = dataSource.getStringSet("items", new HashSet<>());
        List<String> itemsArray = new ArrayList<>(newItems);
        List<String> sortedItemArray = listSort(itemsArray);
        List<String> newSortedItemArray = checkClosedDue(sortedItemArray);

        assert newItems != null;
        adapter.addAll(newSortedItemArray);

    }

    @Override
    protected void onPause() {
        super.onPause();

        Set<String> items = new HashSet<>();
        for ( int i = 0; i < adapter.getCount(); i ++){
            items.add(adapter.getItem(i));
        }

        dataSource.edit().clear().putStringSet("items", items).apply();
    }

    public void backToMenuClicked(View view){
        Intent backToMenu = new Intent(this, MenuPageActivity.class);
        startActivity(backToMenu);
    }

    public void plannerAddClicked(View view){
        Intent plannerAdd = new Intent(this, PlannerAddItemActivity.class);
        startActivity(plannerAdd);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<String> checkClosedDue(List<String> sortedItemArray) {
        String listString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDate currentDate =LocalDate.now();

        for(int i = 0; i < sortedItemArray.size(); i ++){

            listString = sortedItemArray.get(i).split(System.lineSeparator())[0].replace(",", "");
            LocalDate dateString = LocalDate.parse(listString, formatter);

            long days = ChronoUnit.DAYS.between(dateString, currentDate);
            int daysInt = (int) -days;
            String newSortItemFormat = sortedItemArray.get(i).split(System.lineSeparator())[0] +
                    "\n" + sortedItemArray.get(i).split(System.lineSeparator())[1] +
                    "\n" + sortedItemArray.get(i).split(System.lineSeparator())[2] +
                    "\n" + "\n";

            if (daysInt < 0 ){
                overdue.add(i);
                String newSortItem = newSortItemFormat + "\t\t\t\t\t\t\t\t\t\t\t\tOver Due";
                sortedItemArray.set(i, newSortItem);
            } else if (daysInt <= 7) {
                closedDue.add(i);
                String newSortItem = newSortItemFormat + "\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(daysInt) + " left \n" + "\t\t\t\t\t\t\t\t\t\t\t\tDue day soon";
                sortedItemArray.set(i, newSortItem);
            }else{
                String newSortItem = newSortItemFormat + "\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(daysInt) + " left";
                sortedItemArray.set(i, newSortItem);
            }
        }
        return sortedItemArray;
    }



}