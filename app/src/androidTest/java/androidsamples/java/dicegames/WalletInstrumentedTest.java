package androidsamples.java.dicegames;

import static org.junit.Assert.assertEquals;

import android.content.Context;

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
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    assertEquals("androidsamples.java.dicegames", appContext.getPackageName());
  }

  @Test
  public void diceClickCount() {
    // Check initial total dice rolls count is 0
    onView(withId(R.id.tv_total_dice_rolled)).check(matches(withText("Total Dice Rolls: 0")));

    // Simulate two die rolls and check the total dice rolls count
    onView(withId(R.id.tv_die_roll)).perform(click());
    onView(withId(R.id.tv_die_roll)).perform(click());
    onView(withId(R.id.tv_total_dice_rolled)).check(matches(withText("Total Dice Rolls: 2")));

    // Simulate two more die rolls and check the updated count
    onView(withId(R.id.tv_die_roll)).perform(click());
    onView(withId(R.id.tv_die_roll)).perform(click());
    onView(withId(R.id.tv_total_dice_rolled)).check(matches(withText("Total Dice Rolls: 4")));
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

}
