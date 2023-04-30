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

import java.lang.ref.WeakReference;

public class TutorialExampleActivity extends AppCompatActivity {

    //buttons for interactivity
    private Button Continue;
    private Button brandspoofing_preview;
    private Button suspicousemail_preview;
    private Button oobh_preview;
    private Button grammarmistakes_preview;
    private Button shockvalue_preview;
    private Button suspicouslinks_preview;
    //imageview for example images
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorialexample);

        Continue = (Button) findViewById(R.id.Continue);
        brandspoofing_preview = (Button) findViewById(R.id.brandspoofing_preview);
        suspicousemail_preview = (Button) findViewById(R.id.suspicousemail_preview);
        oobh_preview = (Button) findViewById(R.id.oobh_preview);
        grammarmistakes_preview = (Button) findViewById(R.id.grammarmistakes_preview);
        shockvalue_preview = (Button) findViewById(R.id.shockvalue_preview);
        suspicouslinks_preview = (Button) findViewById(R.id.suspicouslinks_preview);
        imageView = (ImageView) findViewById(R.id.imageView4);


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTutorialLook();
            }
        });
        brandspoofing_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 10;
                byte[] imageBytes = GameFunctionActivity.GetImageBitmap(id);
                ImageSet imageSet = new ImageSet();
                imageSet.execute(imageBytes);
            }
        });
        suspicousemail_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 6;
                byte[] imageBytes = GameFunctionActivity.GetImageBitmap(id);
                ImageSet imageSet = new ImageSet();
                imageSet.execute(imageBytes);
            }
        });
        oobh_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 12;
                byte[] imageBytes = GameFunctionActivity.GetImageBitmap(id);
                ImageSet imageSet = new ImageSet();
                imageSet.execute(imageBytes);
            }
        });
        grammarmistakes_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 8;
                byte[] imageBytes = GameFunctionActivity.GetImageBitmap(id);
                ImageSet imageSet = new ImageSet();
                imageSet.execute(imageBytes);
            }
        });
        shockvalue_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 2;
                byte[] imageBytes = GameFunctionActivity.GetImageBitmap(id);
                ImageSet imageSet = new ImageSet();
                imageSet.execute(imageBytes);
            }
        });
        suspicouslinks_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = 4;
                byte[] imageBytes = GameFunctionActivity.GetImageBitmap(id);
                ImageSet imageSet = new ImageSet();
                imageSet.execute(imageBytes);
            }
        });
    }

    private void openTutorialLook() {
        Intent intent = new Intent(this, TutorialImageLookActivity.class);
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