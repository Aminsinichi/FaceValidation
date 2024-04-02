package com.example.facevalidation;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        TextView textView = findViewById(R.id.textView);

        String fileUriStr = getIntent().getStringExtra("fileUri");
        Uri fileUri = Uri.parse(fileUriStr);

        try {
            InputStream inputStream = getContentResolver().openInputStream(fileUri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }

            textView.setText(builder.toString());

            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            textView.setText("Error reading file");
        }
    }
}
