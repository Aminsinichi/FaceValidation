package com.example.facevalidation;

import android.os.Environment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHelper {

    private static final String CSV_HEADER = "ID,Age,Gender,Date,Photo,Response,Time";

    public static void writeLine(String participantId, String age, String gender, String date, String photoName, String response, String time) {
        File csvFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                "FaceValidation_" + participantId + ".csv"); // Changed filename
        FileWriter writer;

        try {
            writer = new FileWriter(csvFile, true);

            if (csvFile.length() == 0) {
                writer.append(CSV_HEADER);
                writer.append('\n');
            }

            writer.append(participantId);
            writer.append(',');
            writer.append(age);
            writer.append(',');
            writer.append(gender);
            writer.append(',');
            writer.append(date);
            writer.append(',');
            writer.append(photoName);
            writer.append(',');
            writer.append(response);
            writer.append(',');
            writer.append(time);
            writer.append('\n');

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
