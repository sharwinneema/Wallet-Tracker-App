package androidsamples.java.dicegames;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

public class DiceGamesPrefs {
    private static final String TAG = "DiceGamesPrefs";
    private static final String PREFS_KEY_BALANCE = "PREFS_KEY_BALANCE";
    private static final String PREFS_KEY_DIE_VALUE = "PREFS_KEY_DIE_VALUE";
    private static final String PREFS_KEY_PREVIOUS_ROLL = "PREFS_KEY_PREVIOUS_ROLL";
    private static final String PREFS_KEY_SIXES_ROLLED = "PREFS_KEY_SIXES_ROLLED";
    private static final String PREFS_KEY_TOTAL_ROLLS = "PREFS_KEY_TOTAL_ROLLS";
    private static final String PREFS_KEY_DOUBLE_SIXES = "PREFS_KEY_DOUBLE_SIXES";
    private static final String PREFS_KEY_DOUBLE_OTHERS = "PREFS_KEY_DOUBLE_OTHERS";
    private static final String SHARED_PREF_FILE = "androidsamples.java.dicegames.SHARED_PREF_FILE";

    private static SharedPreferences getSharedPrefs(@NotNull Context context) {
        return context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
    }

    public static int getBalance(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        return prefs.getInt(PREFS_KEY_BALANCE, 0);
    }

    public static void setBalance(@NotNull Context context, int balance) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_BALANCE, balance);
        editor.apply();
        Log.d(TAG, "Storing balance: " + balance);
    }

    public static int getDieValue(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        return prefs.getInt(PREFS_KEY_DIE_VALUE, 1); // Default die value is 1
    }

    public static void setDieValue(@NotNull Context context, int dieValue) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_DIE_VALUE, dieValue);
        editor.apply();
    }

    public static int getPreviousRoll(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        return prefs.getInt(PREFS_KEY_PREVIOUS_ROLL, -1); // Default no previous roll
    }

    public static void setPreviousRoll(@NotNull Context context, int previousRoll) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_PREVIOUS_ROLL, previousRoll);
        editor.apply();
    }

    public static int getSixesRolled(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        return prefs.getInt(PREFS_KEY_SIXES_ROLLED, 0);
    }

    public static void setSixesRolled(@NotNull Context context, int sixesRolled) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_SIXES_ROLLED, sixesRolled);
        editor.apply();
    }

    public static int getTotalRolls(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        return prefs.getInt(PREFS_KEY_TOTAL_ROLLS, 0);
    }

    public static void setTotalRolls(@NotNull Context context, int totalRolls) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_TOTAL_ROLLS, totalRolls);
        editor.apply();
    }

    public static int getDoubleSixes(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        return prefs.getInt(PREFS_KEY_DOUBLE_SIXES, 0);
    }

    public static void setDoubleSixes(@NotNull Context context, int doubleSixes) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_DOUBLE_SIXES, doubleSixes);
        editor.apply();
    }

    public static int getDoubleOthers(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        return prefs.getInt(PREFS_KEY_DOUBLE_OTHERS, 0);
    }

    public static void setDoubleOthers(@NotNull Context context, int doubleOthers) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_DOUBLE_OTHERS, doubleOthers);
        editor.apply();
    }
}
