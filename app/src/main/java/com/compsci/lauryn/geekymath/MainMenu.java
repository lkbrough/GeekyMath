package com.compsci.lauryn.geekymath;//You can ignore this, it's just linking everything together and everything might explode if you remove it(JK)

import android.content.Intent;//These are just simple imports for android. MAKE SURE TO LEAVE THESE HERE!
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainMenu extends AppCompatActivity {//Bare nessesity to Extend some sort of Activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {//Creates the initial look of the Activity (Activates XML Page)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//This creates the little bar at the top, this class really has no use for it but leave it here anyway so design is consistant
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//Ignore this, this too is for that Little bar at the top.
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void load(View view){//The only method I needed to write to make it work, I personally think I did pretty good job JK I tried
        Intent intent=new Intent(this, Test.class);//Pass between MainMenu and Test Activities
        if(view.getId()==R.id.learn)
            intent=new Intent(this, Learn.class);//Change it just because we need to access Learn instead
        else if(view.getId()==R.id.l3)//Set Global Variables
            Test.level=3;
        else if(view.getId()==R.id.l2)//Ditto
            Test.level=2;
        else
            Test.level=1;//Ditto as well as a best error send
        Test.quest=true;//Just to generate a new question in Test
        startActivity(intent);//Onto Test! (or possibly Learn)
    }

}
