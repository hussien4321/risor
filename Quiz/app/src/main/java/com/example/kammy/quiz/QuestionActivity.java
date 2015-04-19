package com.example.kammy.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by damel on 18/04/15.
 */
public class QuestionActivity extends ActionBarActivity {

    int score;
    int lives;
    Button ans1,ans2,ans3,ans4;
    TextView ques,points,hp;
    boolean activebuttons;
    long presstime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        lives = 3;
        score = 0;
        activebuttons = true;
        final ArrayList<String[]> QandAs = (ArrayList<String[]>)getIntent().getSerializableExtra("QandAs");
        ques = (TextView) findViewById(R.id.textView4);
        points = (TextView) findViewById(R.id.textView5);
        hp = (TextView) findViewById(R.id.textView6);
        ans1 = (Button) findViewById(R.id.answer1);
        ans2 = (Button) findViewById(R.id.answer2);
        ans3 = (Button) findViewById(R.id.answer3);
        ans4 = (Button) findViewById(R.id.answer4);
        ques.setText(QandAs.get(score)[0]);
        ans1.setText(QandAs.get(score)[1]);
        ans2.setText(QandAs.get(score)[2]);
        ans3.setText(QandAs.get(score)[3]);
        ans4.setText(QandAs.get(score)[4]);
        hp.setText("LIVES: "+lives);
        points.setText("SCORE: "+score);
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()-presstime>500){
                    activebuttons=true;
                    ans1.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans3.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans4.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ques.setText(QandAs.get(score)[0]);
                    ans1.setText(QandAs.get(score)[1]);
                    ans2.setText(QandAs.get(score)[2]);
                    ans3.setText(QandAs.get(score)[3]);
                    ans4.setText(QandAs.get(score)[4]);
                    hp.setText("LIVES: "+lives);
                    points.setText("SCORE: "+score);
                }
                if(activebuttons) {
                    activebuttons = false;
                    presstime=System.currentTimeMillis();
                    if (QandAs.get(score)[5].equals(QandAs.get(score)[1])) {
                        score++;
                    } else {
                        lives--;
                        if (lives == 0) {
                            Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                            i.putExtra("score", score);
                            finish();
                            startActivity(i);
                        } else {
                            ans1.setBackgroundColor(getResources().getColor(R.color.red));
                            Toast.makeText(getApplicationContext(), "Wrong Answer, try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (QandAs.size() == score) {
                        Intent win = new Intent(getApplicationContext(), VictoryActivity.class);
                        finish();
                        startActivity(win);
                    } else {
                        ques.setText(QandAs.get(score)[0]);
                        ans1.setText(QandAs.get(score)[1]);
                        ans2.setText(QandAs.get(score)[2]);
                        ans3.setText(QandAs.get(score)[3]);
                        ans4.setText(QandAs.get(score)[4]);
                        hp.setText("LIVES: "+lives);
                        points.setText("SCORE: "+score);
                    }
                }
            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()-presstime>500){
                    activebuttons=true;
                    ans1.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans3.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans4.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ques.setText(QandAs.get(score)[0]);
                    ans1.setText(QandAs.get(score)[1]);
                    ans2.setText(QandAs.get(score)[2]);
                    ans3.setText(QandAs.get(score)[3]);
                    ans4.setText(QandAs.get(score)[4]);
                    hp.setText("LIVES: "+lives);
                    points.setText("SCORE: "+score);
                }
                if(activebuttons) {
                    activebuttons = false;
                    if (QandAs.get(score)[5].equals(QandAs.get(score)[2])) {
                        score++;
                    } else {
                        lives--;
                        if (lives == 0) {
                            Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                            i.putExtra("score", score);
                            finish();
                            startActivity(i);
                        } else {
                            ans2.setBackgroundColor(getResources().getColor(R.color.red));
                            Toast.makeText(getApplicationContext(), "Wrong Answer, try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (QandAs.size() == score) {
                        Intent win = new Intent(getApplicationContext(), VictoryActivity.class);
                        finish();
                        startActivity(win);
                    } else {
                        ques.setText(QandAs.get(score)[0]);
                        ans1.setText(QandAs.get(score)[1]);
                        ans2.setText(QandAs.get(score)[2]);
                        ans3.setText(QandAs.get(score)[3]);
                        ans4.setText(QandAs.get(score)[4]);
                        hp.setText("LIVES: "+lives);
                        points.setText("SCORE: "+score);
                    }
                }
            }
        });
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(System.currentTimeMillis()-presstime>500){
                    activebuttons=true;
                    ans1.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans3.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans4.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ques.setText(QandAs.get(score)[0]);
                    ans1.setText(QandAs.get(score)[1]);
                    ans2.setText(QandAs.get(score)[2]);
                    ans3.setText(QandAs.get(score)[3]);
                    ans4.setText(QandAs.get(score)[4]);
                    hp.setText("LIVES: "+lives);
                    points.setText("SCORE: "+score);
                }
                if(activebuttons) {
                    activebuttons = false;
                    if (QandAs.get(score)[5].equals(QandAs.get(score)[3])) {
                        score++;
                    } else {
                        lives--;
                        if (lives == 0) {
                            Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                            i.putExtra("score", score);
                            finish();
                            startActivity(i);
                        } else {
                            ans3.setBackgroundColor(getResources().getColor(R.color.red));
                            Toast.makeText(getApplicationContext(), "Wrong Answer, try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (QandAs.size() == score) {
                        Intent win = new Intent(getApplicationContext(), VictoryActivity.class);
                        finish();
                        startActivity(win);
                    } else {
                        ques.setText(QandAs.get(score)[0]);
                        ans1.setText(QandAs.get(score)[1]);
                        ans2.setText(QandAs.get(score)[2]);
                        ans3.setText(QandAs.get(score)[3]);
                        ans4.setText(QandAs.get(score)[4]);
                        hp.setText("LIVES: "+lives);
                        points.setText("SCORE: "+score);
                    }
                }
            }
        });
        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()-presstime>500){
                    activebuttons=true;
                    ans1.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans2.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans3.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ans4.setBackgroundColor(getResources().getColor(R.color.yellow));
                    ques.setText(QandAs.get(score)[0]);
                    ans1.setText(QandAs.get(score)[1]);
                    ans2.setText(QandAs.get(score)[2]);
                    ans3.setText(QandAs.get(score)[3]);
                    ans4.setText(QandAs.get(score)[4]);
                    hp.setText("LIVES: "+lives);
                    points.setText("SCORE: "+score);
                }
                if(activebuttons) {
                    activebuttons = false;
                    if (QandAs.get(score)[5].equals(QandAs.get(score)[4])) {
                        score++;
                    } else {
                        lives--;
                        if (lives == 0) {
                            Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                            i.putExtra("score", score);
                            finish();
                            startActivity(i);
                        } else {
                            ans4.setBackgroundColor(getResources().getColor(R.color.red));
                            Toast.makeText(getApplicationContext(), "Wrong Answer, try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (QandAs.size() == score) {
                        Intent win = new Intent(getApplicationContext(), VictoryActivity.class);
                        finish();
                        startActivity(win);
                    } else {
                        ques.setText(QandAs.get(score)[0]);
                        ans1.setText(QandAs.get(score)[1]);
                        ans2.setText(QandAs.get(score)[2]);
                        ans3.setText(QandAs.get(score)[3]);
                        ans4.setText(QandAs.get(score)[4]);
                        hp.setText("LIVES: "+lives);
                        points.setText("SCORE: "+score);
                    }
                }
            }
        });
    }


}

