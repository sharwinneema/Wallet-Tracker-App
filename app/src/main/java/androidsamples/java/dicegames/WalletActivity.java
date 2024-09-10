package androidsamples.java.dicegames;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;


public class WalletActivity extends AppCompatActivity {

  private WalletViewModel viewModel;

  // UI Elements
  private TextView tvCoins;
  private TextView tvDieRoll;
  private TextView tvPreviousRoll;
  private TextView tvStats;
  private TextView tvDoubleStats;

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
    tvStats = findViewById(R.id.tv_stats);
    tvDoubleStats = findViewById(R.id.tv_double_stats);

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
    tvCoins.setText(getString(R.string.coins_label, viewModel.balance()));

    // Update the die roll display
    tvDieRoll.setText(String.valueOf(viewModel.dieValue()));

    // Update the previous roll
    tvPreviousRoll.setText(getString(R.string.previous_roll_label, viewModel.previousRoll()));

    // Update the stats for sixes rolled and total dice rolls
    tvStats.setText(getString(R.string.stats_label, viewModel.singleSixes(), viewModel.totalRolls()));

    // Update the stats for double sixes and double other numbers
    tvDoubleStats.setText(getString(R.string.double_stats_label, viewModel.doubleSixes(), viewModel.doubleOthers()));
  }

}