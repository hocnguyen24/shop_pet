package com.example.pet_pet.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferenceUtils {
    private static final String KEY_FIRST_RUN = "first_run";

    public static boolean isFirstRun(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean(KEY_FIRST_RUN, true);
        if (isFirstRun) {
            // Đây là lần đầu tiên, thực hiện các công việc cần thiết
            // ...

            // Sau khi hoàn thành công việc, đặt isFirstRun thành false
            preferences.edit().putBoolean(KEY_FIRST_RUN, false).apply();
        }else {
            Log.d("PreferenceUtils", "Not the first run");
        }

        return isFirstRun;
    }
}
