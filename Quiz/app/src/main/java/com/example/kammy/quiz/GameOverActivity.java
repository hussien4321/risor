package com.example.kammy.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by damel on 18/04/15.
 */
public class GameOverActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button button = (Button)findViewById(R.id.button3);
        TextView t = (TextView) findViewById(R.id.textView5);
        int score = getIntent().getIntExtra("score", 0);
        t.setText("SCORE: " + score);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoadingActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}