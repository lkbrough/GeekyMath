package com.compsci.lauryn.geekymath;//Ignore this, links the project together.

import android.content.Intent;//Ignore the imports, only brings the methods and classes that are being used.
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Answer extends AppCompatActivity {//Basics extend something and because this uses the ActionBar, it made since.


    @Override
    protected void onCreate(Bundle savedInstanceState) {//Activated as soon as the Activity begins
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);//Sets the view to the specifications provided by XML document

        Intent intent=getIntent();//Retrieving the information put in the intent from the Test aka Correct or not Correct
        String correct=intent.getStringExtra(Test.correct);//Storing the information

        TextView textView=(TextView) findViewById(R.id.response);//Retrieving the textview with pointless information
        textView.setTextSize(40);//Makes it bigger because the activity is blank except for this and the button
        textView.setText(correct);//Setting it to the proper response
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//For now ignore this, this comment will be removed once I explain this more IRL
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Same as Above
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.main_menu){//Adds the button to go to MainMenu and clears streak
            Test test=new Test();
            test.stop();
            Test.practice=false;
            Intent intent=new Intent(this,MainMenu.class);
            startActivity(intent);
            return true;
        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.action_settings) {//Pointless for now
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cont(View view){//Basic Method only to return to Test Activity and generate a new Question
        Intent intent=new Intent(this, Test.class);
        Test.quest=true;
        startActivity(intent);
    }

}
