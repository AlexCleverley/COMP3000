package com.example.comp3000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayImageLookActivity extends AppCompatActivity {

    private Button Continue;
    private ImageView imageView;
    private TextView Signamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playimagelook);

        Continue = (Button) findViewById(R.id.Continue);
        imageView = (ImageView) findViewById(R.id.PlayLook_imageView);
        Signamount = (TextView) findViewById(R.id.textView2);

        // Get random level ID
        int int_random = GameFunctionActivity.GetRandomNumber();

        //get bitmap of random image for level
        Bitmap imageBitmap = GameFunctionActivity.GetImageBitmap(int_random);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(imageBitmap);

        //get signamount of random level
        String signAmountText = GameFunctionActivity.GetSignAmount(int_random);
        //insert new text into Signamount textview
        Signamount.setText(signAmountText.toCharArray(), 0, signAmountText.length());

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageGuess();
            }
        });
    }

    private void openImageGuess() {
        Intent intent = new Intent(this, PlayImageGuessActivity.class);
        startActivity(intent);
        finish();
    }

}