package com.example.decibel;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.decibel.R;

public class MainActivity extends AppCompatActivity {
    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendDataToActivity (int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View myView){
        String resourceId = getResources().getResourceEntryName(myView.getId());   //myView.getId() returns int that android makes for the ImgBtn, the rest of ir helps discover the String ID,(S1001, S1002)
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("temasek", "The id of the pressed ImageButton is: " + resourceId);
        sendDataToActivity(currentArrayIndex);
    }

}