package com.compsci.lauryn.geekymath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class Test extends AppCompatActivity {

    public static int num;
    public static int ans;
    public int guess;
    public static int level;
    public int temp;
    public static String correct="answer";
    public static String instructions;
    public static int streak=0;
    public static boolean type;
    public static boolean quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /*if(level==2) //Moved to Resume to work better with activity life cycle
            generateProblem2();
        else if(level==3)
            generateProblem3();
        else
            generateProblem1();

        TextView directions=(TextView) findViewById(R.id.direct);
        directions.setText(instructions);

        TextView textView=(TextView) findViewById(R.id.question);
        textView.setTextSize(40);
        textView.setText(Integer.toString(num)+"\r\n");
        textView.setLines(1);

        TextView number=(TextView) findViewById(R.id.StreakNumber);
        number.setTextSize(20);
        number.setText(Integer.toString(streak));*/
    }

    protected void onResume(){
        super.onResume();

        if(quest) {
            if (level == 2)
                generateProblem2();
            else if (level == 3)
                generateProblem3();
            else
                generateProblem1();

            quest=false;


            TextView directions = (TextView) findViewById(R.id.direct);
            directions.setText(instructions);

            TextView textView = (TextView) findViewById(R.id.question);
            textView.setTextSize(40);
            textView.setText(Integer.toString(num) + "\r\n");
            textView.setLines(1);

            TextView number = (TextView) findViewById(R.id.StreakNumber);
            number.setTextSize(20);
            number.setText(Integer.toString(streak));

            EditText editText = (EditText) findViewById(R.id.answerBox);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            if (type)
                editText.setKeyListener(DigitsKeyListener.getInstance("01"));
            else
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        }
        else{

            TextView directions = (TextView) findViewById(R.id.direct);
            directions.setText(instructions);

            TextView textView = (TextView) findViewById(R.id.question);
            textView.setTextSize(40);
            textView.setText(Integer.toString(num) + "\r\n");
            textView.setLines(1);

            TextView number = (TextView) findViewById(R.id.StreakNumber);
            number.setTextSize(20);
            number.setText(Integer.toString(streak));

            EditText editText = (EditText) findViewById(R.id.answerBox);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            if (type) {
                editText.setKeyListener(DigitsKeyListener.getInstance("01"));
            } else
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.main_menu){
            Intent intent=new Intent(this,MainMenu.class);
            stop();
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void generateProblem1(){
        instructions="Convert the following number to binary.";
        if(streak<=25&&streak>15){
            num=(int)(Math.random()*64);
        }
        else if(streak<=45&&streak>25){
            num=(int)(Math.random()*128);
        }
        else if(streak>=65){
            num=(int)(Math.random()*256);
        }
        else{
            num=(int)(Math.random()*32);
        }
        type=true;
        ans=Integer.parseInt(Integer.toString(num, 2));
    }

    public void generateProblem2(){
        instructions="Convert the following number to decimal.";
        if(streak<=25&&streak>15){
            ans=(int)(Math.random()*64);
        }
        else if(streak<=45&&streak>25){
            ans=(int)(Math.random()*128);
        }
        else if(streak>=65){
            ans=(int)(Math.random()*256);
        }
        else{
            ans=(int)(Math.random()*32);
        }
        type=false;
        num=Integer.parseInt(Integer.toString(ans,2));
    }

    public void generateProblem3(){
        temp=(int)(Math.random()*2);
        if(temp==1)
            generateProblem1();
        else
            generateProblem2();
    }

    public void receive(View view){
        Intent intent=new Intent(this, Answer.class);
        EditText editText=(EditText) findViewById(R.id.answerBox);

        String message=editText.getText().toString();
        if(message.isEmpty())
            correct="Enter an answer";
        else {
            guess = Integer.parseInt(message);
            check(guess);
        }

        intent.putExtra(correct, correct);
        startActivity(intent);
    }

    private void check(int num1){

        if(num1==ans) {
            correct="Correct";
            streak++;
        }
        else {
            correct="Incorrect";
            streak=0;
        }
    }

    public void stop(){
        streak=0;
    }

}
