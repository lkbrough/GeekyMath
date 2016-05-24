package com.compsci.betadevelopment.geekymath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivitywasd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitywasd);
    }
    public void Loading(View view){
        Intent intent=new Intent(this, Test.class);
        if(view.getId()==R.id.l3)//Set Global Variables
            Test.level=3;
        else if(view.getId()==R.id.l2)//Ditto
            Test.level=2;
        else if(view.getId()==R.id.l4)
            Test.level=4;
        else if(view.getId()==R.id.l5){
            Test.level=5;
        }
        else if(view.getId()==R.id.l6){
            Test.level=6;
        }
        else
            Test.level=1;//Ditto as well as a best error send
        Test.quest=true;//Just to generate a new question in Test


        startActivity(intent);
    }


}
