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
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class TutorialImageGuessActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button Continue;
    private ToggleButton brandspoofing_toggle;
    private ToggleButton suspicousemail_toggle;
    private ToggleButton oobh_toggle;
    private ToggleButton grammarmistakes_toggle;
    private ToggleButton shockvalue_toggle;
    private ToggleButton suspicouslinks_toggle;
    private String ToggleButtonValues = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorialimageguess);

        Continue = (Button) findViewById(R.id.Continue);
        brandspoofing_toggle = (ToggleButton) findViewById(R.id.brandspoofing_toggle);
        suspicousemail_toggle = (ToggleButton) findViewById(R.id.suspicousemail_toggle);
        oobh_toggle = (ToggleButton) findViewById(R.id.oobh_toggle);
        grammarmistakes_toggle = (ToggleButton) findViewById(R.id.grammarmistakes_toggle);
        shockvalue_toggle = (ToggleButton) findViewById(R.id.shockvalue_toggle);
        suspicouslinks_toggle = (ToggleButton) findViewById(R.id.suspicouslinks_toggle);
        imageView = (ImageView) findViewById(R.id.imageView2);


        //get previous record id
        int int_random = GameFunctionActivity.CallRandomNumber();

        //get bitmap of random image for level
        byte[] imageBytes = GameFunctionActivity.GetImageBitmap(int_random);
        ImageSet imageSet = new ImageSet();
        imageSet.execute(imageBytes);

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when user clicks continue, check the state of the toggle buttons and add to list array
                if (suspicousemail_toggle.isChecked()) {ToggleButtonValues += "1";} else {ToggleButtonValues += "0";}
                if (oobh_toggle.isChecked()) {ToggleButtonValues += ",1";} else {ToggleButtonValues += ",0";}
                if (grammarmistakes_toggle.isChecked()) {ToggleButtonValues += ",1";} else {ToggleButtonValues += ",0";}
                if (shockvalue_toggle.isChecked()) {ToggleButtonValues += ",1";} else {ToggleButtonValues += ",0";}
                if (suspicouslinks_toggle.isChecked()) {ToggleButtonValues += ",1";} else {ToggleButtonValues += ",0";}
                if (brandspoofing_toggle.isChecked()) {ToggleButtonValues += ",1";} else {ToggleButtonValues += ",0";}

                //send to game function activity to compare against level list and return score
                GameFunctionActivity.SaveButtonInput(ToggleButtonValues);

                openTutorialScore();
            }
        });
    }

    private void openTutorialScore() {
        Intent intent = new Intent(this, TutorialScoreActivity.class);
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