package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TestDetails extends AppCompatActivity {

    public static final String MSG = "com.example.quiz.TAKETEST";
    public String testName;
    public Integer pos;




    public void continueTest(View view){
        Intent intent = new Intent(this, takeTest.class);
        intent.putExtra(MSG,testName);
        //Toast.makeText(this,testName,Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }




    public void cancelTest(View view) {
        Toast.makeText(this, "YOU HAVE CANCELLED THE TEST ! RETURNED TO HOME PAGE", Toast.LENGTH_SHORT).show();
        finish();
    }





    @Override
    public void onBackPressed() {
        Toast.makeText(this,"      YOU CANNOT RETURN BACK FROM HERE!\n        USE CANCEL TEST INSTEAD",Toast.LENGTH_SHORT).show();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);
        Intent intent = getIntent();
        Spinner spinner_1 = findViewById(R.id.test_names);

        List<String> list = new ArrayList<>();
        //        list.add("RANJITH");
        //        list.add("ARUN");
        //        list.add("JEESMON");
        //        list.add("NISAM");
        //        list.add("SREEJITH");
        //        list.add("SANJAY");
        //        list.add("AKSHY");
        //        list.add("FIROZ");
        //        list.add("RAHUL");
        //        list.add("ARJUN");
        //        list.add("SAVIYO");
        //        list.add("VISHNU");


        try {
            InputStreamReader is = new InputStreamReader(this.getAssets().open("quiznames.txt"));
            Scanner sc = new Scanner(is);
            while (sc.hasNextLine()){
                list.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        TextView txt1 = findViewById(R.id.label_test_select);
        txt1.setText("TEST NAME : "+list.get(0)+"\nIS SELECTED, CONTINUE TO PROCEED.");
        testName = list.get(0);
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                testName = ((TextView) view).getText().toString();
                txt1.setText("TEST NAME : "+testName+"\nIS SELECTED, CONTINUE TO PROCEED.");
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}