package com.example.facevalidation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TaskActivity extends AppCompatActivity {

    private ImageView imageView;
    private ArrayList<Integer> imageResIds;
    private int currentIndex = 0;
    private String participantId = "";
    private String participantAge = "";
    private String participantGender = "";
    private long imageDisplayTime; // Added this variable to hold the time when the image is displayed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        imageView = findViewById(R.id.image_view);

        loadImages();
        showParticipantIdDialog();
        loadNextImage();

        findViewById(R.id.button_angry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordResponseAndLoadNextImage(((Button)v).getText().toString());
            }
        });

        findViewById(R.id.button_surprise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordResponseAndLoadNextImage(((Button)v).getText().toString());
            }
        });

        findViewById(R.id.button_sad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordResponseAndLoadNextImage(((Button)v).getText().toString());
            }
        });

        findViewById(R.id.button_neutral).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordResponseAndLoadNextImage(((Button)v).getText().toString());
            }
        });

        findViewById(R.id.button_fearful).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordResponseAndLoadNextImage(((Button)v).getText().toString());
            }
        });

        findViewById(R.id.button_happy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordResponseAndLoadNextImage(((Button)v).getText().toString());
            }
        });

        findViewById(R.id.button_none).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordResponseAndLoadNextImage(((Button)v).getText().toString());
            }
        });
    }

    private void loadImages() {
        ArrayList<Integer> set1 = new ArrayList<>();
        ArrayList<Integer> set2 = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            int resId = getResources().getIdentifier("photo_" + i, "drawable", getPackageName());
            set1.add(resId);
        }
        for (int i = 61; i <= 110; i++) {
            int resId = getResources().getIdentifier("photo_" + i, "drawable", getPackageName());
            set2.add(resId);
        }
        Collections.shuffle(set1);
        Collections.shuffle(set2);

        imageResIds = new ArrayList<>();

        // Randomly decide which set to show first
        if (new Random().nextBoolean()) {
            imageResIds.addAll(set1);
            imageResIds.addAll(set2);
        } else {
            imageResIds.addAll(set2);
            imageResIds.addAll(set1);
        }
    }

    private void loadNextImage() {
        if (currentIndex >= imageResIds.size()) {
            // The task is over
            Intent intent = new Intent(TaskActivity.this, CompletionActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        imageDisplayTime = System.currentTimeMillis();  // record the time
        imageView.setImageResource(imageResIds.get(currentIndex));
        currentIndex++;
    }

    private void showParticipantIdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_participant_id, null);
        builder.setView(dialogView);

        final EditText editParticipantId = dialogView.findViewById(R.id.edit_participant_id);
        final EditText editParticipantAge = dialogView.findViewById(R.id.edit_participant_age);
        final EditText editParticipantGender = dialogView.findViewById(R.id.edit_participant_gender);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                participantId = editParticipantId.getText().toString();
                participantAge = editParticipantAge.getText().toString();
                participantGender = editParticipantGender.getText().toString();
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void recordResponseAndLoadNextImage(String selectedEmotion) {
        long responseTime = System.currentTimeMillis();
        long reactionTime = responseTime - imageDisplayTime;  // Calculate reaction time

        String photoName = getResources().getResourceEntryName(imageResIds.get(currentIndex - 1)) + ".png";
        String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
        String reactionTimeStr = String.valueOf(reactionTime);

        CSVHelper.writeLine(participantId, participantAge, participantGender, date, photoName, selectedEmotion, reactionTimeStr);

        loadNextImage();
    }
}
