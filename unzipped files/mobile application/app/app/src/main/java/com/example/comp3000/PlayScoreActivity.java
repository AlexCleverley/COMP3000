package com.example.comp3000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayScoreActivity extends AppCompatActivity {
    private Button ReturnToMenu;
    private Button PlayAgain;
    private TextView ScoreReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playscore);

        ReturnToMenu = findViewById(R.id.return_to_menu);
        PlayAgain = findViewById(R.id.play_again);
        ScoreReport = findViewById(R.id.textView6);

        String ScoreText = GameFunctionActivity.ReturnScore();
        ScoreReport.setText(ScoreText.toCharArray(), 0, ScoreText.length());

        ReturnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openstartView();
            }
        });
        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayImageLook();
            }
        });

    }

    private void openPlayImageLook() {
        Intent intent = new Intent(this, PlayImageLookActivity.class);
        startActivity(intent);
        finish();
    }

    private void openstartView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}