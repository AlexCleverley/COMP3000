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

import java.text.MessageFormat;

public class TutorialImageLookActivity extends AppCompatActivity {
    private Button Continue;
    private ImageView imageView;
    private TextView Signamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorialimagelook);

        Continue = (Button) findViewById(R.id.Continue);
        imageView = (ImageView) findViewById(R.id.imageView);
        Signamount = (TextView) findViewById(R.id.textView2);

        //get random level ID
        int int_random = GameFunctionActivity.GetRandomNumber();

        //get bitmap of random image for level
        byte[] imageBytes = GameFunctionActivity.GetImageBitmap(int_random);
        ImageSet imageSet = new ImageSet();
        imageSet.execute(imageBytes);

        //get signamount of random level
        String signAmountText = GameFunctionActivity.GetSignAmount(int_random);
        //insert new text into Signamount textview
        Signamount.setText(signAmountText.toCharArray(), 0, signAmountText.length());

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTutorialGuess();
            }
        });
    }

    private void openTutorialGuess() {
        Intent intent = new Intent(this, TutorialImageGuessActivity.class);
        startActivity(intent);
    }

    public class ImageSet extends AsyncTask<byte[],Void, Bitmap> {
        private ImageView imageView;


        @Override
        protected Bitmap doInBackground(byte[]... imagedata) {
            byte[] imageBytes = imagedata[0];
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if( bitmap != null) {
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }

}