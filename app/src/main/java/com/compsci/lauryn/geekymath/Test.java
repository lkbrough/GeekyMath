package com.compsci.lauryn.geekymath;//The Basic link for the whole project again
//This is the activity/class where the bulk of the app happens aka where the magic happens. There is a lot of different things happening in this class so just bare with me and try and to read all the comments.
import android.content.Intent;//The imports to bring what is needed including all the android classes.
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class Test extends AppCompatActivity {//Extending to make it an activity

    public static int num;//This is the actual number the user is being tested on
    public static int ans;//This is the answer to the number that the user is tested on.
    public int guess;//This is more of a temporary variable with one purpose, to hold the users response.
    public static int level;//This is the level or mode that the user is playing on. It tells the app what the user wants to play, converting to binary, converting to decimal, or a mix of both at random.
    public int temp;//Generic Temporary Variable... What else can I say?
    public static String correct="answer";//This is going to be sent to the Answer class in order to diplayed.
    public static String instructions;//This is the instructions that are displayed before the problem. This could be a set string, but it needs to change depending on the level. 
    public static int streak=0;//This is the players streak. It serves to purposes, making the player feel proud of how many problems they have gotten in a row and to determine if it needs to get harder by increasing the number of bits.
    public static boolean type;//This is set to limit the keyboard or not. It depends on the problem type on the keyboard.
    public static boolean quest;//This is set to generate the problem or not. This only exists because on screen rotation, Android restarts the activity and destroys a lot of data.

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Basic common onCreate to actually start the view for an activity.
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

    protected void onResume(){//This is a tad bit unique for this class. If you want to know why I am doing this, refer to developer.android.com and check out develop training lesson over managing the activity cycle.
        super.onResume();

        if(quest) {//This is to determine if it needs to generate a new problem. Added because of screen rotation.
            if (level == 2)//Determine level
                generateProblem2();//Go check this out, Binary to decimal
            else if (level == 3)//Detemine Level again
                generateProblem3();//Go check this out, Mix the problems up a bit
            else//If there is some major error that happened, in order to not startle the user, just have them do basic decimal to binary
                generateProblem1();//Go check this out, Decimal to Binary

            quest=false;//Set this in order to not generate a new problem... Stupid Screen Rotation
        }
        TextView directions = (TextView) findViewById(R.id.direct);//Find the blank text where the directions should be
        directions.setText(instructions);//Set the directions, it really helps to be relying on some text so hopefully the user can understand

        TextView textView = (TextView) findViewById(R.id.question);//Find the blank text where the Question should be
        textView.setTextSize(40);//Make it bigger so it is the focus on the screen
        textView.setText(Integer.toString(num) + "\r\n");//Set the text to the question and go to new line just to clean it up
        textView.setLines(1);//Just to clean it up by making it take a whole line

        TextView number = (TextView) findViewById(R.id.StreakNumber);//Find the view where the streak should be
        number.setTextSize(20);//Size down the text so it isn't the main focus on the screen
        number.setText(Integer.toString(streak));//Set the actual streak to the text

        EditText editText = (EditText) findViewById(R.id.answerBox);//Find the 
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        if (type) {
            editText.setKeyListener(DigitsKeyListener.getInstance("01"));
        } else
            editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
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
