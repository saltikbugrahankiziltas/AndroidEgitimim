package com.example.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText,timeText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=(TextView)findViewById(R.id.timeText);
        scoreText=(TextView)findViewById(R.id.scoreText);
        imageView=(ImageView) findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView7=(ImageView)findViewById(R.id.imageView7);
        imageView8=(ImageView)findViewById(R.id.imageView8);
        imageView9=(ImageView)findViewById(R.id.imageView9);

        imageArray=new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();

        score=0;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                timeText.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Baştan başlasın mı?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // restart yapacak
                        // 1 tane  intent var ve restart attık.
                        Intent intent= getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start(); // CountDownTimer başlasın
    }
    public void skoruartir(View view){
        // Bir buton veya view'a onclick metodu atarken bir görünüm tarafından çağrılacağını bildiğimiz için yazılır.
        score++;
        scoreText.setText("Score: "+score);
    }
    public void hideImages(){

        handler=new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random=new Random();
                int i =random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };

        handler.post(runnable);

    }
}