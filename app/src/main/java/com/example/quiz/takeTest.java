package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


public class takeTest extends AppCompatActivity {
    public int i=1;
    String testName = "";
    List <String> questions = new ArrayList<>();
    Integer totalQues = 0;
    List <Integer> crt = new ArrayList<>();
    Integer quesnodis = 0;
    List <Integer> ansEntry = new ArrayList<>();


    public void getQuiz() {
        try {
            InputStreamReader is = new InputStreamReader(this.getAssets().open(testName));
            Scanner sc = new Scanner(is);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                questions.add(line);
            }
            totalQues = questions.size()/6;
        }
        catch (IOException e) {
            e.printStackTrace();

        }

        finally{
            int j = 5;

            for (int qno = 1; qno<=totalQues;j+=6){
                qno+=1;
                String line = questions.get(j);
                crt.add( Integer.parseInt((line.subSequence(line.length() - 1, line.length())).toString()));


            }


        }


    }

    public void generateQuestion(){
        if (quesnodis < totalQues){
            int idxno= quesnodis*6;
            RadioGroup rgId = findViewById(R.id.opGroup);
            rgId.clearCheck();
            TextView ques = findViewById(R.id.question);
            RadioButton op1 = findViewById(R.id.op1);
            RadioButton op2 = findViewById(R.id.op2);
            RadioButton op3 = findViewById(R.id.op3);
            RadioButton op4 = findViewById(R.id.op4);
            RadioButton op5 = findViewById(R.id.op5);
            String questext = questions.get(idxno).replace("\\n","\r\n").substring(9).replace("\\t","\t\t");
            ques.setText(questions.get(idxno).substring(0,8)+"  "+(quesnodis+1)+":\t"+questext);
            op1.setText(questions.get(idxno+1).substring(2).replace("\\n","\r\n").replace("\\t","\t\t"));
            op2.setText(questions.get(idxno+2).substring(2).replace("\\n","\r\n").replace("\\t","\t\t"));
            op3.setText(questions.get(idxno+3).substring(2).replace("\\n","\r\n").replace("\\t","\t\t"));
            op4.setText(questions.get(idxno+4).substring(2).replace("\\n","\r\n").replace("\\t","\t\t"));
            op5.setText("I DON'T KNOW THE ANSWER");
            quesnodis++;




        }
        else{
            //CALCULATING MARKS AND PERCENTAGE AND THEN REDIRECTING TO RESULT PAGE
            Integer quesAnsCheck = 1;
            Integer totalMarks = 0;
            while(quesAnsCheck<=totalQues) {
                if (crt.get(quesAnsCheck - 1).equals(ansEntry.get(quesAnsCheck - 1))) {
                    totalMarks += 4;
                } else if (ansEntry.get(quesAnsCheck - 1) == 5) {
                    totalMarks += 0;
                } else {
                    totalMarks -= 1;
                }
                quesAnsCheck++;

            }


            Intent intent = new Intent(this,ResultPage.class);
            intent.putExtra("totalMarks",totalMarks.toString());
            intent.putExtra("totalQues",totalQues.toString());
            intent.putExtra("testname",testName);
            startActivity(intent);
            finish();

        }
    }


    /*
    public void generateQuestion(){
        if (i==1){
            RadioGroup rgId = findViewById(R.id.opGroup);
            rgId.clearCheck();
            TextView ques = findViewById(R.id.question);
            ques.setText("Q1 . IN WHICH LANGUAGE IS THIS TEST DEVELOPED?");
            RadioButton op1 = findViewById(R.id.op1);
            RadioButton op2 = findViewById(R.id.op2);
            RadioButton op3 = findViewById(R.id.op3);
            RadioButton op4 = findViewById(R.id.op4);
            RadioButton op5 = findViewById(R.id.op5);
            //op1.setChecked(false);
            //op2.setChecked(false);
            //op3.setChecked(false);
            //op4.setChecked(false);
            //op5.setChecked(false);
            op1.setText("PYTHON");
            op2.setText("C++");
            op3.setText("KOTLIN");
            op4.setText("JAVA");
            op5.setText("I DON'T KNOW THE ANSWER");


        }

        else if (i==2){
            RadioGroup rgId = findViewById(R.id.opGroup);
            rgId.clearCheck();
            TextView ques = findViewById(R.id.question);
            ques.setText("Q2 . WHO DEVELOPED THIS APP?");
            RadioButton op1 = findViewById(R.id.op1);
            RadioButton op2 = findViewById(R.id.op2);
            RadioButton op3 = findViewById(R.id.op3);
            RadioButton op4 = findViewById(R.id.op4);
            RadioButton op5 = findViewById(R.id.op5);
            //op1.setChecked(false);
            //op2.setChecked(false);
            //op3.setChecked(false);
            //op4.setChecked(false);
            //op5.setChecked(false);
            op1.setText("MR. SUDESH");
            op2.setText("MRS. ABHA");
            op3.setText("ABHAY");
            op4.setText("ANCHAL");
            op5.setText("I DON'T KNOW THE ANSWER");

        }
        else{
            Intent intent = new Intent(this,ResultPage.class);
            startActivity(intent);
            finish();
        }
    }
    */


    public void cancelTest(View view) {
        Toast.makeText(this,"YOU HAVE CANCELLED THE TEST ! RETURNED TO HOME PAGE",Toast.LENGTH_SHORT).show();
        finish();

    }

    public void submitAndNext(View view){
        RadioGroup rgId = findViewById(R.id.opGroup);
        int  rbId = rgId.getCheckedRadioButtonId();
        if (rbId==-1){
            Toast.makeText(this,"Please Select Any One Option",Toast.LENGTH_SHORT).show();

        }
        else if (1==quesnodis) {
            Button cancelButton = findViewById(R.id.button_cancel);
            cancelButton.setEnabled(false);
            RadioButton rButton =findViewById(rbId);
            Integer opChoose = Integer.parseInt((rButton.getTag()).toString());
            ansEntry.add(opChoose);
            generateQuestion();
        }
        else{
            RadioButton rButton =findViewById(rbId);
            Integer opChoose = Integer.parseInt((rButton.getTag()).toString());
            ansEntry.add(opChoose);
            generateQuestion();
        }

    }


    /*
    public void submitAndNext(View view){

        RadioGroup rgId = findViewById(R.id.opGroup);
        int  rbId = rgId.getCheckedRadioButtonId();

        if (rbId==-1){
            Toast.makeText(this,"Please Select Any One Option",Toast.LENGTH_SHORT).show();

        }
        else if (1==i){
            Button cancelButton = findViewById(R.id.button_cancel);
            cancelButton.setEnabled(false);
            i++;
            //generateQuestion();

        }
        else {
            i++;
            //generateQuestion();

        }

    }

     */


    @Override
    public void onBackPressed() {
        Toast.makeText(this,"YOU CANNOT RETURN BACK FROM TEST! \n     USE CANCEL TEST INSTEAD",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(TestDetails.MSG);

        testName = msg+".txt";
        //Toast.makeText(this,testName,Toast.LENGTH_SHORT).show();
        getQuiz();
        generateQuestion();


    }
}