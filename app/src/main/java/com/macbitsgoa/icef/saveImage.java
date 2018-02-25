package com.macbitsgoa.icef;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

public class saveImage {

    public String saveImageLocally(Bitmap image, String name, String path) {
        OutputStream output;
        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath()
                + "/ICEF/" + (path.length() == 0 ? "" : path + "/"));
        dir.mkdirs();

        File file;
        if (TextUtils.isEmpty(name)) {

            Date date = Calendar.getInstance().getTime();
            file = new File(dir, "ICEF" + date.getTime());

        } else
            file = new File(dir, name + ".jpg");

        try {

            output = new FileOutputStream(file);

            image.compress(Bitmap.CompressFormat.JPEG, 100, output);
            output.flush();
            output.close();
            return file.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
