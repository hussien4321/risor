package com.example.kammy.quiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button button = (Button)findViewById(R.id.button);
        final ArrayList<String[]> arrayList = (ArrayList<String[]>)getIntent().getSerializableExtra("QandA");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), QuestionActivity.class);
                i.putExtra("QandAs",arrayList);
                startActivity(i);

            }
        });
    }

}
