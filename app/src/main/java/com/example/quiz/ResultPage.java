package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ResultPage extends AppCompatActivity {

    public void returnHome(View view){
        finish();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"  YOU CANNOT RETURN BACK TO TEST!  \n  USE RETURN -  HOME PAGE",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        Intent intent = getIntent();
        Integer marks = Integer.parseInt(intent.getStringExtra("totalMarks"));
        Integer totalques = Integer.parseInt(intent.getStringExtra("totalQues"));
        String testname = intent.getStringExtra("testname").toString();
        TextView testnameOutput = findViewById(R.id.testNameReport);
        testnameOutput.setText("Test Name - "+testname.substring(0,testname.length()-4));
        TextView marksOutput = findViewById(R.id.marks);
        marksOutput.setText("Marks : "+marks+"/"+(totalques*4));
        TextView perOutput = findViewById(R.id.percent);
        if (marks<0){
            perOutput.setText("Percentage : -"+(Math.abs(marks)/4.0/totalques*100.0)+" %");
        }
        else{
            perOutput.setText("Percentage : "+(marks/4.0/totalques*100.0)+" %");
        }


    }

}

