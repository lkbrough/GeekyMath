package com.compsci.lauryn.geekymath;//The Basic link for the whole project again
//This is the activity/class where the bulk of the app happens aka where the magic happens. There is a lot of different things happening in this class so just bare with me and try and to read all the comments.

import android.content.Intent;//The imports to bring what is needed including all the android classes.
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Test extends AppCompatActivity {//Extending to make it an activity

    private static String num;//This is the actual number the user is being tested on
    private static String ans;//This is the answer to the number that the user is tested on.
    public static int level;//This is the level or mode that the user is playing on. It tells the app what the user wants to play, converting to binary, converting to decimal, or a mix of both at random.
    private int temp;//Generic Temporary Variable... What else can I say?
    public static String correct = "answer";//This is going to be sent to the Answer class in order to displayed.
    private static String instructions;//This is the instructions that are displayed before the problem. This could be a set string, but it needs to change depending on the level.
    private static int streak = 0;//This is the players streak. It serves to purposes, making the player feel proud of how many problems they have gotten in a row and to determine if it needs to get harder by increasing the number of bits.
    public static boolean type;//This is set to limit the keyboard or not. It depends on the problem type on the keyboard.
    public static boolean quest;//This is set to generate the problem or not. This only exists because on screen rotation, Android restarts the activity and destroys a lot of data.
    public static boolean practice = false;
    public static int count = 0;
    private static boolean hexKey;
    private String prevAns="";
    private String[] prevQuest=new String[5];
    private static int loc=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Basic common onCreate to actually start the view for an activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if(level==4||level==5||level==6) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.Layout), R.string.hexInput, Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }
    }

    protected void onResume() {//This is a tad bit unique for this class. If you want to know why I am doing this, refer to developer.android.com and check out develop training lesson over managing the activity cycle.
        super.onResume();

        if (practice) {
            if (count < 10)
                practiceProblems();
            else {
                Intent intent = new Intent(this, Learn.class);
                if (!Learn.passed) {
                    Learn.mid = true;
                    Learn.passed = true;
                    count = 10;
                    streak = 0;
                } else if (Learn.passed) {
                    Learn.mid = false;
                }
                startActivity(intent);
            }
        } else if (quest) {//This is to determine if it needs to generate a new problem. Added because of screen rotation.
            if (level == 2)//Determine level
                generateProblem2();//Go check this out, Binary to decimal
            else if (level == 3)//Determine Level again
                generateProblem3();//Go check this out, Mix the problems up a bit
            else if (level == 4)
                generateProblem4();//Decimal to Hex
            else if (level == 5)
                generateProblem5();
            else if (level == 6)
                generateProblem6();
            else//If there is some major error that happened, in order to not startle the user, just have them do basic decimal to binary
                generateProblem1();//Go check this out, Decimal to Binary

            quest = false;//Set this in order to not generate a new problem... Stupid Screen Rotation
        }
        TextView directions = (TextView) findViewById(R.id.direct);//Find the blank text where the directions should be
        directions.setText(instructions);//Set the directions, it really helps to be relying on some text so hopefully the user can understand

        TextView textView = (TextView) findViewById(R.id.question);//Find the blank text where the Question should be
        textView.setTextSize(40);//Make it bigger so it is the focus on the screen
        textView.setText(num + "\r\n");//Set the text to the question and go to new line just to clean it up
        textView.setLines(1);//Just to clean it up by making it take a whole line

        TextView number = (TextView) findViewById(R.id.StreakNumber);//Find the view where the streak should be
        number.setTextSize(20);//Size down the text so it isn't the main focus on the screen
        number.setText(Integer.toString(streak));//Set the actual streak to the text

        TextView ansBox = (TextView) findViewById(R.id.ansBox);
        ansBox.setTextSize(20);
        if(!prevAns.equals("")) {
            ansBox.setText("Previous Correct Answer:" + prevAns);
        }
        else{
            ansBox.setText("");
        }

        EditText editText = (EditText) findViewById(R.id.answerBox);//Find the text box where the answer would be
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);//Limit the keyboard to numbers
        if (hexKey) {
            if (type) {
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789ABCDEF"));
            } else
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        } else {
            if (type) {
                editText.setKeyListener(DigitsKeyListener.getInstance("01"));//Limit the keyboard to only the number 0 and 1
            } else
                editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));//Limit the keyboard... Well not really
        }
    }

    public void onPause() {
        super.onPause();
        quest = true;
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

        if (id == R.id.main_menu) {
            Intent intent = new Intent(this, MainMenu.class);
            stop();
            practice = false;
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void practiceProblems() {
        if (count == 0) {
            num = "1";
        } else if (count == 1) {
            num = "2";
        } else if (count == 2) {
            num = "3";
        } else if (count == 3) {
            num = "4";
        } else if (count == 4) {
            num = "7";
        } else if (count == 5) {
            num = "8";
        } else if (count == 6) {
            num = "9";
        } else if (count == 7) {
            num = "10";
        } else if (count == 8) {
            num = "12";
        } else if (count == 9) {
            num = "13";
        }
        type = true;
        instructions = "Convert the following number to binary.";
        ans = Integer.toString(Integer.parseInt(num), 2);
    }

    public void generateProblem1() {//First Problem generator, decimal to binary! Let's git started!
        instructions = "Convert the following number to binary.";//Start by setting the instructions just to get this out of the way.
        if (streak <= 25 && streak > 15) {//This is when the streak comes in. If they get so far adjust the difficulty
            num = Integer.toString((int) (Math.random() * 64));
        } else if (streak <= 45 && streak > 25) {//Ditto
            num = Integer.toString((int) (Math.random() * 128));
        } else if (streak >= 65) {//Hardest! I didn't want to go higher than this because this is already pretty insane
            num = Integer.toString((int) (Math.random() * 256));
        } else {
            num = Integer.toString((int) (Math.random() * 32));
        }
        if(noRepeat(num)){
            generateProblem1();
        }
        prevQuest[loc++]=num;
        if(loc>4){
            loc=0;
        }
        hexKey = false;
        type = true;//Used to limit the keyboard
        ans = Integer.toString(Integer.parseInt(num), 2);//Get the answer in integer form just so it's smaller than a string to save!
    }

    public void generateProblem2() {//Generate the binary to decimal!
        instructions = "Convert the following number to decimal.";//Set the instructions, out of the way now.
        if (streak <= 25 && streak > 15) {//Same as above! Just look at generateProblem1()
            temp = (int) (Math.random() * 64);//STOP! Okay so you hopefully noticed that this is the opposite from above! That is because you can literally use the same methods just flip it!
        } else if (streak <= 45 && streak > 25) {//Ditto
            temp = (int) (Math.random() * 128);
        } else if (streak >= 65) {//Ditto
            temp = (int) (Math.random() * 256);
        } else {
            temp = (int) (Math.random() * 32);
        }
        hexKey = false;
        type = false;//Don't limit the keyboard...
        ans = Integer.toString(temp);
        num = Integer.toString(temp, 2);//Look above at that really really really really really really really long comment. Longer than this one.
    }

    public void generateProblem3() {//Interesting one... Let's mix it up!
        temp = (int) (Math.random() * 2);//Pick a number any number! As long as its a 1 or a 2... This is what really mixes it up! Yay!
        if (temp == 1)//If its one... go generate a decimal to binary problem
            generateProblem1();
        else//Otherwise do a binary to decimal... Makes since...
            generateProblem2();
    }

    public void generateProblem4() {
        instructions = "Convert the following number to hexadecimal.";
        if (streak <= 25 && streak > 15) {
            num = Integer.toString((int) (Math.random() * 64));
        } else if (streak <= 45 && streak > 25) {
            num = Integer.toString((int) (Math.random() * 128));
        } else if (streak >= 65) {
            num = Integer.toString((int) (Math.random() * 256));
        } else {
            num = Integer.toString((int) (Math.random() * 32));
        }
        hexKey = true;
        type = false;
        ans = Integer.toString(Integer.parseInt(num), 16);
    }

    public void generateProblem5() {
        instructions = "Convert the following hexadecimal number to decimal.";
        if (streak <= 25 && streak > 15) {
            temp = (int) (Math.random() * 64);
        } else if (streak <= 45 && streak > 25) {
            temp = (int) (Math.random() * 128);
        } else if (streak >= 65) {
            temp = (int) (Math.random() * 256);
        } else {
            temp = (int) (Math.random() * 32);
        }
        hexKey = true;
        type = true;
        num=Integer.toString(temp,16);
        ans=Integer.toString(temp);
    }

    public void generateProblem6() {//Interesting one... Let's mix it up!
        temp = (int) (Math.random() * 2);//Pick a number any number! As long as its a 1 or a 2... This is what really mixes it up! Yay!
        if (temp == 1)//If its one... go generate a decimal to binary problem
            generateProblem4();
        else//Otherwise do a binary to decimal... Makes since...
            generateProblem5();
    }


    public void receive(View view) {//This will actually get the answer that has been entered, let's take a look.
        //Intent intent=new Intent(this, Answer.class);//Connections, yay.

        TextView tv1 = (TextView) findViewById(R.id.answer);//Yay, replacing connections with a more local connection.

        EditText editText = (EditText) findViewById(R.id.answerBox);//Get the answer box... again.

        String message = editText.getText().toString();//Get the answer in the box
        if (message.isEmpty())//Sanity Check! Cuz you never know with people and because I already limited the keyboard they can't enter a letter.
            correct = "Enter an answer";//Yell at the user for being overly silly!
        else {
            check(message);//Scroll just a little bit to look at this method...
        }

        tv1.setTextSize(40);//It would be small otherwise...
        tv1.setText(correct);//Instead of sending it to answer
        editText.setText("");//If we didn't do this the edit box would still have the previous answer
        quest = true;//Almost forgot this because it is changed in answer
        onResume();//This should work, refer up toward the top

        //intent.putExtra(correct, correct);//Send the response to answer class!
        //startActivity(intent);//Let's start this party! Not really... The answer class is kinda lame...
    }

    private void check(String num1) {//This is only seperate because I needed to sort the ideas out in my head... Yeah... It makes since

        if (num1.equals(ans)) {//Isn't this obvious? It's only right if they entered the right number... So it should be equal to... Right?
            correct = "Correct";//Derp
            streak++;//NEVER FORGET THE SEMICOLON! JK don't forget to increase the streak and make the player feel proud... But don't forget semicolons either...
            if (practice) {
                count++;
            }
            prevAns="";
        } else {//I don't know what else it could be... I already Sanity Checked an blank answer out...
            correct = "Incorrect";
            streak = 0;//I would decrease it by one but this game as no checkpoints, there is now starting at a later level... Or maybe we could add that in later...
            prevAns=ans;
        }
    }

    public void stop() {//This is just used for a Error that was happening earlier and seemed like the best patch to cover it with...
        //update(streak);
        streak = 0;
        count = 0;
        for(int x=0;x<prevQuest.length;x++){
            prevQuest[x]="null";
        }
    }

    public boolean noRepeat(String num1){
        for(String x:prevQuest){
            if(num1.equals(x)){
                return true;
            }
        }
        return false;
    }

}