package androidsamples.java.dicegames;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.jetbrains.annotations.NotNull;

public class DiceGamesPrefs {
    private static final String TAG = "DiceGamesPrefs";
    private static final String PREFS_KEY_BALANCE = "PREFS_KEY_BALANCE";
    private static final String PREFS_KEY_SINGLE_SIXES = "PREFS_KEY_SINGLE_SIXES";
    private static final String PREFS_KEY_DOUBLE_SIXES = "PREFS_KEY_DOUBLE_SIXES";
    private static final String PREFS_KEY_DOUBLE_OTHERS = "PREFS_KEY_DOUBLE_OTHERS";
    private static final String PREFS_KEY_PREVIOUS_ROLL = "PREFS_KEY_PREVIOUS_ROLL";
    private static final String PREFS_KEY_TOTAL_ROLLS = "PREFS_KEY_TOTAL_ROLLS";
    private static final String SHARED_PREF_FILE = "androidsamples.java.dicegames.SHARED_PREF_FILE";

    private static SharedPreferences getSharedPrefs(@NotNull Context context) {
        return context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
    }

    // Method to retrieve balance
    static int getBalance(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        int balance = prefs.getInt(PREFS_KEY_BALANCE, 0);
        Log.d(TAG, "Retrieving balance: " + balance);
        return balance;
    }

    // Method to store balance
    static void setBalance(@NotNull Context context, int balance) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_BALANCE, balance);
        editor.apply();
        Log.d(TAG, "Storing balance: " + balance);
    }

    // Method to retrieve single sixes count
    static int getSingleSixes(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        int singleSixes = prefs.getInt(PREFS_KEY_SINGLE_SIXES, 0);
        Log.d(TAG, "Retrieving single sixes: " + singleSixes);
        return singleSixes;
    }

    // Method to store single sixes count
    static void setSingleSixes(@NotNull Context context, int singleSixes) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_SINGLE_SIXES, singleSixes);
        editor.apply();
        Log.d(TAG, "Storing single sixes: " + singleSixes);
    }

    // Method to retrieve double sixes count
    static int getDoubleSixes(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        int doubleSixes = prefs.getInt(PREFS_KEY_DOUBLE_SIXES, 0);
        Log.d(TAG, "Retrieving double sixes: " + doubleSixes);
        return doubleSixes;
    }

    // Method to store double sixes count
    static void setDoubleSixes(@NotNull Context context, int doubleSixes) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_DOUBLE_SIXES, doubleSixes);
        editor.apply();
        Log.d(TAG, "Storing double sixes: " + doubleSixes);
    }

    // Method to retrieve double others count
    static int getDoubleOthers(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        int doubleOthers = prefs.getInt(PREFS_KEY_DOUBLE_OTHERS, 0);
        Log.d(TAG, "Retrieving double others: " + doubleOthers);
        return doubleOthers;
    }

    // Method to store double others count
    static void setDoubleOthers(@NotNull Context context, int doubleOthers) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_DOUBLE_OTHERS, doubleOthers);
        editor.apply();
        Log.d(TAG, "Storing double others: " + doubleOthers);
    }

    // Method to retrieve the previous roll
    static int getPreviousRoll(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        int previousRoll = prefs.getInt(PREFS_KEY_PREVIOUS_ROLL, -1);
        Log.d(TAG, "Retrieving previous roll: " + previousRoll);
        return previousRoll;
    }

    // Method to store the previous roll
    static void setPreviousRoll(@NotNull Context context, int previousRoll) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_PREVIOUS_ROLL, previousRoll);
        editor.apply();
        Log.d(TAG, "Storing previous roll: " + previousRoll);
    }

    // Method to retrieve total rolls
    static int getTotalRolls(@NotNull Context context) {
        SharedPreferences prefs = getSharedPrefs(context);
        int totalRolls = prefs.getInt(PREFS_KEY_TOTAL_ROLLS, 0);
        Log.d(TAG, "Retrieving total rolls: " + totalRolls);
        return totalRolls;
    }

    // Method to store total rolls
    static void setTotalRolls(@NotNull Context context, int totalRolls) {
        SharedPreferences prefs = getSharedPrefs(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREFS_KEY_TOTAL_ROLLS, totalRolls);
        editor.apply();
        Log.d(TAG, "Storing total rolls: " + totalRolls);
    }
}
