package androidsamples.java.dicegames;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WalletActivity extends AppCompatActivity {

  private WalletViewModel viewModel;

  // UI Elements
  private TextView tvCoins;
  private TextView tvDieRoll;
  private TextView tvPreviousRoll;
  private TextView tvSixesRolled;
  private TextView tvTotalDiceRolled;
  private TextView tvDoubleSixes;
  private TextView tvDoubleOthers;

  // Keys for saving state
  private static final String TAG = "WalletActivity";
  private static final String KEY_BALANCE = "balance";
  private static final String KEY_DIE_VALUE = "die_value";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wallet);

    // Initialize ViewModel
    viewModel = new ViewModelProvider(this).get(WalletViewModel.class);

    // Bind UI elements
    tvCoins = findViewById(R.id.tv_coins);
    tvDieRoll = findViewById(R.id.tv_die_roll);
    tvPreviousRoll = findViewById(R.id.tv_previous_roll);
    tvSixesRolled = findViewById(R.id.tv_sixes_rolled);
    tvTotalDiceRolled = findViewById(R.id.tv_total_dice_rolled);
    tvDoubleSixes = findViewById(R.id.tv_double_sixes);
    tvDoubleOthers = findViewById(R.id.tv_double_others);

    // Check if there's saved state
    if (savedInstanceState != null) {
      int savedBalance = savedInstanceState.getInt(KEY_BALANCE, 0);
      int savedDieValue = savedInstanceState.getInt(KEY_DIE_VALUE, 1);
      viewModel.setBalance(savedBalance);
      viewModel.setDieValue(savedDieValue);
    }

    // Set initial values for UI from ViewModel
    updateUI();

    // Set up a click listener on the die to roll it
    tvDieRoll.setOnClickListener(v -> {
      viewModel.rollDie(); // Roll the die
      updateUI(); // Update the UI after rolling

      if (viewModel.hasEarnedCoins()) {
        Toast.makeText(this, R.string.congratulations, Toast.LENGTH_SHORT).show();
      }
      if (viewModel.hasLossCoins()) {
        Toast.makeText(this, R.string.loss_message, Toast.LENGTH_SHORT).show();
      }
    });
  }

  /**
   * Updates all UI elements with the latest data from the ViewModel.
   */
  private void updateUI() {
    // Update the coins display
    String coinText = getString(R.string.coins_label, viewModel.balance());
    tvCoins.setText(coinText);
    tvCoins.setContentDescription("Total Coins: " + viewModel.balance());

    // Update the die roll display
    String dieRollText = String.valueOf(viewModel.dieValue());
    tvDieRoll.setText(dieRollText);
    tvDieRoll.setContentDescription("Current Die Roll: " + viewModel.dieValue());

    // Update the previous roll
    String previousRollText = getString(R.string.previous_roll_label, viewModel.previousRoll());
    tvPreviousRoll.setText(previousRollText);
    tvPreviousRoll.setContentDescription("Previous Roll: " + viewModel.previousRoll());

    // Update the stats for sixes rolled
    String sixesRolledText = getString(R.string.sixes_rolled_label, viewModel.singleSixes());
    tvSixesRolled.setText(sixesRolledText);
    tvSixesRolled.setContentDescription("Number of Sixes Rolled: " + viewModel.singleSixes());

    // Update the total dice rolls
    String totalDiceRolledText = getString(R.string.total_dice_rolled_label, viewModel.totalRolls());
    tvTotalDiceRolled.setText(totalDiceRolledText);
    tvTotalDiceRolled.setContentDescription("Total Dice Rolls: " + viewModel.totalRolls());

    // Update the stats for double sixes
    String doubleSixesText = getString(R.string.double_sixes_label, viewModel.doubleSixes());
    tvDoubleSixes.setText(doubleSixesText);
    tvDoubleSixes.setContentDescription("Number of Double Sixes Rolled: " + viewModel.doubleSixes());

    // Update the stats for double other numbers
    String doubleOthersText = getString(R.string.double_others_label, viewModel.doubleOthers());
    tvDoubleOthers.setText(doubleOthersText);
    tvDoubleOthers.setContentDescription("Number of Double Other Numbers Rolled: " + viewModel.doubleOthers());
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.d(TAG, "onSaveInstanceState");

    // Save balance and die value to outState
    outState.putInt(KEY_BALANCE, viewModel.balance());
    outState.putInt(KEY_DIE_VALUE, viewModel.dieValue());

    Log.d(TAG, "Saved: balance = " + viewModel.balance() + ", die = " + viewModel.dieValue());
  }
}
