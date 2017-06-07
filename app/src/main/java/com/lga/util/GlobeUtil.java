package com.lga.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by Jay on 2017/5/21.
 */

public class GlobeUtil {

    public static void log2File(String log, File file) {
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.print(log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getFromAssets(Context context, String fileName) {
        StringBuffer result = new StringBuffer();
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) result.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
