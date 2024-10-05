package androidsamples.java.dicegames;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.Rule;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.test.uiautomator.UiDevice;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WalletInstrumentedTest {

  @Rule
  public ActivityScenarioRule<WalletActivity> activityRule = new ActivityScenarioRule<>(WalletActivity.class);

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = getInstrumentation().getTargetContext();
    assertEquals("androidsamples.java.dicegames", appContext.getPackageName());
  }


  @Test
  public void initialSingleSixesCounts() {
    // Check initial sixes rolled count is 0
    onView(withId(R.id.tv_sixes_rolled)).check(matches(withText("Sixes Rolled: 0")));
  }

  @Test
  public void initialDoubleCounts() {
    // Check initial double sixes count is 0
    onView(withId(R.id.tv_double_sixes)).check(matches(withText("Double Sixes: 0")));

    // Check initial double others count is 0
    onView(withId(R.id.tv_double_others)).check(matches(withText("Double Others: 0")));
  }

  @Test
  public void initialCoinCount() {
    // Check initial coin count is 0
    onView(withId(R.id.tv_coins)).check(matches(withText("Coins: 0")));
  }
  @Test
  public void rollDie_incrementsTotalRolls() {
    // Perform one die roll
    onView(withId(R.id.tv_die_roll)).perform(click());

    // Check that the total rolls have increased by 1
    onView(withId(R.id.tv_total_dice_rolled)).check(matches(withText("Total Dice Rolls: 1")));
  }

  @Test
  public void rollDie_singleSix_addsFiveCoins() {
    // Store the initial coin count
    final int[] initialCoinCount = {0};

    // Check initial coin count
    onView(withId(R.id.tv_coins)).check((view, noViewFoundException) -> {
      String coinText = ((TextView) view).getText().toString(); // Get text from the TextView
      initialCoinCount[0] = Integer.parseInt(coinText.replaceAll("\\D+", "")); // Extract numerical part
    });

    // Keep rolling the die until a six is rolled
    while (true) {
      // Perform a click to roll the die
      onView(withId(R.id.tv_die_roll)).perform(click());

      // Check the die value
      final int[] dieValue = {0};
      onView(withId(R.id.tv_die_roll)).check((view, noViewFoundException) -> {
        String dieRollText = ((TextView) view).getText().toString();
        dieValue[0] = Integer.parseInt(dieRollText);
      });

      // If a 6 is rolled, check if the coin count has increased by 5
      if (dieValue[0] == 6) {
        onView(withId(R.id.tv_coins)).check((view, noViewFoundException) -> {
          String updatedCoinText = ((TextView) view).getText().toString();
          int updatedCoinCount = Integer.parseInt(updatedCoinText.replaceAll("\\D+", ""));
          assertEquals(initialCoinCount[0] + 5, updatedCoinCount);
        });
        break; // Exit the loop once the condition is met
      }
    }
  }

  @Test
  public void rollDie_singleSix_incrementsSixesRolled() {
    // Store the initial sixes rolled count
    final int[] initialSixesRolled = {0};

    // Check initial sixes rolled count
    onView(withId(R.id.tv_sixes_rolled)).check((view, noViewFoundException) -> {
      String sixesRolledText = ((TextView) view).getText().toString(); // Get text from the TextView
      initialSixesRolled[0] = Integer.parseInt(sixesRolledText.replaceAll("\\D+", "")); // Extract numerical part
    });

    // Keep rolling the die until a six is rolled
    while (true) {
      // Perform a click to roll the die
      onView(withId(R.id.tv_die_roll)).perform(click());

      // Check the die value
      final int[] dieValue = {0};
      onView(withId(R.id.tv_die_roll)).check((view, noViewFoundException) -> {
        String dieRollText = ((TextView) view).getText().toString();
        dieValue[0] = Integer.parseInt(dieRollText);
      });

      // If a 6 is rolled, check if the sixes rolled count has increased by 1
      if (dieValue[0] == 6) {
        onView(withId(R.id.tv_sixes_rolled)).check((view, noViewFoundException) -> {
          String updatedSixesRolledText = ((TextView) view).getText().toString();
          int updatedSixesRolled = Integer.parseInt(updatedSixesRolledText.replaceAll("\\D+", ""));
          assertEquals(initialSixesRolled[0] + 1, updatedSixesRolled);
        });
        break; // Exit the loop once the condition is met
      }
    }
  }

  @Test
  public void checkBalanceAfterAppRelaunch() {
    // Store the initial balance
    final int[] initialBalance = {0};

    // Check the initial balance from the TextView
    onView(withId(R.id.tv_coins)).check((view, noViewFoundException) -> {
      String coinText = ((TextView) view).getText().toString(); // Get text from the TextView
      initialBalance[0] = Integer.parseInt(coinText.replaceAll("\\D+", "")); // Extract the numerical value
    });

    // Press the home button to exit the app
    UiDevice device = UiDevice.getInstance(getInstrumentation());
    device.pressHome();

    // Simulate a wait to mimic relaunch after some time (if needed)
    try {
      Thread.sleep(2000); // Wait for 2 seconds (optional)
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Relaunch the app
    ActivityScenario.launch(WalletActivity.class);

    // Check the balance again after relaunch
    onView(withId(R.id.tv_coins)).check((view, noViewFoundException) -> {
      String updatedCoinText = ((TextView) view).getText().toString(); // Get text from the TextView
      int updatedBalance = Integer.parseInt(updatedCoinText.replaceAll("\\D+", "")); // Extract the numerical value
      assertEquals(initialBalance[0], updatedBalance); // Check if the balance matches the initial balance
    });
  }

  @Test
  public void checkAllFieldsAfterAppRelaunch() {
    // Store the initial values for all fields
    final int[] initialBalance = {0};
    final int[] initialDieRoll = {0};
    final int[] initialPreviousRoll = {0};
    final int[] initialSixesRolled = {0};
    final int[] initialTotalRolls = {0};
    final int[] initialDoubleSixes = {0};
    final int[] initialDoubleOthers = {0};

    // Retrieve and store all the initial field values from the TextViews
    onView(withId(R.id.tv_coins)).check((view, noViewFoundException) -> {
      String coinText = ((TextView) view).getText().toString();
      initialBalance[0] = Integer.parseInt(coinText.replaceAll("\\D+", ""));
    });

    onView(withId(R.id.tv_die_roll)).check((view, noViewFoundException) -> {
      String dieRollText = ((TextView) view).getText().toString();
      initialDieRoll[0] = Integer.parseInt(dieRollText);
    });

    onView(withId(R.id.tv_previous_roll)).check((view, noViewFoundException) -> {
      String previousRollText = ((TextView) view).getText().toString();
      initialPreviousRoll[0] = Integer.parseInt(previousRollText.replaceAll("\\D+", ""));
    });

    onView(withId(R.id.tv_sixes_rolled)).check((view, noViewFoundException) -> {
      String sixesRolledText = ((TextView) view).getText().toString();
      initialSixesRolled[0] = Integer.parseInt(sixesRolledText.replaceAll("\\D+", ""));
    });

    onView(withId(R.id.tv_total_dice_rolled)).check((view, noViewFoundException) -> {
      String totalRollsText = ((TextView) view).getText().toString();
      initialTotalRolls[0] = Integer.parseInt(totalRollsText.replaceAll("\\D+", ""));
    });

    onView(withId(R.id.tv_double_sixes)).check((view, noViewFoundException) -> {
      String doubleSixesText = ((TextView) view).getText().toString();
      initialDoubleSixes[0] = Integer.parseInt(doubleSixesText.replaceAll("\\D+", ""));
    });

    onView(withId(R.id.tv_double_others)).check((view, noViewFoundException) -> {
      String doubleOthersText = ((TextView) view).getText().toString();
      initialDoubleOthers[0] = Integer.parseInt(doubleOthersText.replaceAll("\\D+", ""));
    });

    // Press the home button to exit the app
    UiDevice device = UiDevice.getInstance(getInstrumentation());
    device.pressHome();

    // Simulate a wait to mimic relaunch after some time (if needed)
    try {
      Thread.sleep(2000); // Wait for 2 seconds (optional)
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Relaunch the app
    ActivityScenario.launch(WalletActivity.class);

    // Check all the fields after the app is relaunched

    // Check balance
    onView(withId(R.id.tv_coins)).check((view, noViewFoundException) -> {
      String updatedCoinText = ((TextView) view).getText().toString();
      int updatedBalance = Integer.parseInt(updatedCoinText.replaceAll("\\D+", ""));
      assertEquals(initialBalance[0], updatedBalance);
    });

    // Check die roll
    onView(withId(R.id.tv_die_roll)).check((view, noViewFoundException) -> {
      String updatedDieRollText = ((TextView) view).getText().toString();
      int updatedDieRoll = Integer.parseInt(updatedDieRollText);
      assertEquals(initialDieRoll[0], updatedDieRoll);
    });

    // Check previous roll
    onView(withId(R.id.tv_previous_roll)).check((view, noViewFoundException) -> {
      String updatedPreviousRollText = ((TextView) view).getText().toString();
      int updatedPreviousRoll = Integer.parseInt(updatedPreviousRollText.replaceAll("\\D+", ""));
      assertEquals(initialPreviousRoll[0], updatedPreviousRoll);
    });

    // Check sixes rolled
    onView(withId(R.id.tv_sixes_rolled)).check((view, noViewFoundException) -> {
      String updatedSixesRolledText = ((TextView) view).getText().toString();
      int updatedSixesRolled = Integer.parseInt(updatedSixesRolledText.replaceAll("\\D+", ""));
      assertEquals(initialSixesRolled[0], updatedSixesRolled);
    });

    // Check total dice rolls
    onView(withId(R.id.tv_total_dice_rolled)).check((view, noViewFoundException) -> {
      String updatedTotalRollsText = ((TextView) view).getText().toString();
      int updatedTotalRolls = Integer.parseInt(updatedTotalRollsText.replaceAll("\\D+", ""));
      assertEquals(initialTotalRolls[0], updatedTotalRolls);
    });

    // Check double sixes
    onView(withId(R.id.tv_double_sixes)).check((view, noViewFoundException) -> {
      String updatedDoubleSixesText = ((TextView) view).getText().toString();
      int updatedDoubleSixes = Integer.parseInt(updatedDoubleSixesText.replaceAll("\\D+", ""));
      assertEquals(initialDoubleSixes[0], updatedDoubleSixes);
    });

    // Check double others
    onView(withId(R.id.tv_double_others)).check((view, noViewFoundException) -> {
      String updatedDoubleOthersText = ((TextView) view).getText().toString();
      int updatedDoubleOthers = Integer.parseInt(updatedDoubleOthersText.replaceAll("\\D+", ""));
      assertEquals(initialDoubleOthers[0], updatedDoubleOthers);
    });
  }

  // Add other tests here
}
