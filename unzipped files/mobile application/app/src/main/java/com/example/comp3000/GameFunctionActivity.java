package com.example.comp3000;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameFunctionActivity {

    public static List<Record> records;
    public static int RandomLevelIDStorage;
    public static int TotalSigns;
    public static String UserButtonInput;
    public static String LevelAttributesStorage;

    public static class Access_API extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.0.2.2:5059/api/Record";
            String jsonData = null;

            //create new OkHttp request (Get a URL example on the Okhttp website)
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            //If successful response, store Json array into String for conversion
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    jsonData = response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
           return jsonData;
        }
    }


    public static void AccessAPI() {
        //Access the API record using OkHttp in a background thread
        Access_API access_api = new Access_API();
        String jsonData = null;
        try {
            jsonData = access_api.execute().get();
        } catch (ExecutionException | InterruptedException  e) {
            e.printStackTrace();
        }

        records = new ArrayList<>();
        //convert jsonData String into a new list of Records
        if( jsonData != null) {
            try {

                //create new Json array object with jsonData
                JSONArray jsonRecords = new JSONArray(jsonData);

                //for each record in the json array, copy object values for list
                for (int i = 0; i < jsonRecords.length(); i++) {
                    JSONObject jsonRecord = jsonRecords.getJSONObject(i);
                    int recordId = jsonRecord.getInt("recordID");

                    //get the string of recordImage in jsonRecord then convert to byte[] array
                    String recordImageText = jsonRecord.getString("recordImage");
                    byte[] recordImage = Base64.decode(recordImageText, Base64.DEFAULT);

                    String recordAttributes = jsonRecord.getString("recordAttributes");

                    // add record to arraylist
                    Record levelRecord = new Record(recordId, recordImage, recordAttributes);
                    records.add(levelRecord);

                }
            } catch (IllegalThreadStateException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //Get a random record id number
    public static int GetRandomNumber() {
        Random rand = new Random();

        //produce Random number limit between records 12 - Max of the records list
        int upperbound = records.size();
        int int_random = rand.nextInt(upperbound) - 12;
        RandomLevelIDStorage = int_random;
        return int_random;
    }


    static class ImageDecoder extends AsyncTask<Integer, Void, byte[]> {
        @Override
        protected byte[] doInBackground(Integer... ints) {
            //A byteArray variable for ArrayList recordImage to be copied into
            byte[] imageData = new byte[0];
            int id = ints[0].intValue();

            //for each record in the arraylist
            //ArrayList<Record> records1 = new ArrayList<>(records);
            for(Record record : records) {
                //if the recordID matches, copy the image byte array
                if(record.getRecordID() == id) {
                    imageData = record.getRecordImage();
                }
            }
            return imageData;
        }
    }



    //find record within the arraylist and convert byte array data to bitmap
    public static byte[] GetImageBitmap(int id) {
        byte[] ImageToConvert = new byte[0];
        ImageDecoder imageDecoder = new ImageDecoder();
        try {
            ImageToConvert = imageDecoder.execute(new Integer(id)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if( ImageToConvert != null)
            return ImageToConvert;
        else
            return null;
    }


    //find record within the arraylist and return the attributes of the record
    public static String GetSignAmount(int int_random) {
        //String for Record Attributes to be copied into
        String levelAttributes = "";
        int numberofSigns = 0;
        String signamountText = "";
        LevelAttributesStorage = "";
        //for each record in the arraylist
        for(Record record : records) {

            //if the recordID matches, copy the level attributes
            if(record.getRecordID() == int_random) {
                //store level attributes to variable
                levelAttributes = record.getRecordAttributes();
                // clear level attributes storage
                LevelAttributesStorage = levelAttributes;
            }
        }
        if(levelAttributes != null) {
            //Find out Sign amount
            for (int i = 0; i < levelAttributes.length(); i++) {
                //if the char in levelAttributes is a 1
                if (levelAttributes.charAt(i) == '1') {
                    //add to the sign amount counter
                    numberofSigns++;
                }
            }
        }
        //set number of signs for score use later
        TotalSigns = numberofSigns;

        //set the sign amount into a message for the textview
        if(numberofSigns == 1) {
            signamountText = MessageFormat.format("Look at the Image for {0} sign of a phishing Scam", numberofSigns);
        }
        else
            signamountText = MessageFormat.format("Look at the Image for {0} signs of a phishing Scam", numberofSigns);
        return signamountText;
    }

    //compare user input to level record and return score result
    public static String ReturnScore() {
        String scoreamountText = "";
        int ScoreNumber = 0;

        //compare list of UserButtonInput and LevelAttributes
        if (LevelAttributesStorage != null) {
            for (int i = 0; i < UserButtonInput.length(); i++) {

                //check if Userbuttoninput is a 1 or a 0
                if (UserButtonInput.charAt(i) == '1' || UserButtonInput.charAt(i) == '0') {

                    //check if User guess correctly according to the level attributes
                    if (UserButtonInput.charAt(i) == LevelAttributesStorage.charAt(i)) {
                        ScoreNumber++;
                    }
                }
            }
        }

        //Return score amount in a message for the textview
        if(ScoreNumber == 1) {
            scoreamountText = MessageFormat.format("you detected: {playerscore} out of {signamount} sign", ScoreNumber, TotalSigns);
        }
        else
            scoreamountText = MessageFormat.format("you detected: {playerscore} out of {signamount} signs", ScoreNumber, TotalSigns);
        return scoreamountText;
    }

    //Save users toggle button input
    public static void SaveButtonInput(String ToggleButtonValues) { UserButtonInput = ToggleButtonValues;}

    //Reference previous random record id
    public static int CallRandomNumber() {
        return RandomLevelIDStorage;
    }

}


