package com.example.comp3000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button playbutton;
    private Button tutorialbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //run the accessAPI function in the game function activity
        GameFunctionActivity.AccessAPI();

        playbutton = (Button) findViewById(R.id.button);
        tutorialbutton = (Button) findViewById(R.id.button2);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayImageGuessActivity();
            }
        });

        tutorialbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTutorialExampleActivity();
            }
        });
    }

    public void openPlayImageGuessActivity() {
        Intent intent = new Intent(this, PlayImageLookActivity.class);
        startActivity(intent);
        finish();
    }

    public void openTutorialExampleActivity() {
        Intent intent = new Intent(this, TutorialExampleActivity.class);
        startActivity(intent);
        finish();
    }
}