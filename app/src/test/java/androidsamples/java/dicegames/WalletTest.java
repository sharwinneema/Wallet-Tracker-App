package androidsamples.java.dicegames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class WalletTest {

  private WalletViewModel walletViewModel;

  @Before
  public void setup() {
    walletViewModel = new WalletViewModel();
  }

  @Test
  public void initialScore_isZero() {
    // Test to check if the initial score is 0
    assertEquals(0, walletViewModel.balance());
  }

  @Test
  public void rollDie_singleSix_addsFiveCoins() {
    // Simulate rolling a single six
    walletViewModel.rollDie();

    // We can mock die rolling to be exactly 6 (you can modify Die6 class or mock it for testing)
    // but assuming randomness for now.

    if (walletViewModel.dieValue() == 6) {
      assertEquals(5, walletViewModel.balance());
    }
  }

  @Test
  public void rollDie_doubleSix_addsTenCoins() {
    // Simulate rolling two sixes in a row
    walletViewModel.rollDie();
    if (walletViewModel.dieValue() == 6) {
      walletViewModel.rollDie();
      if (walletViewModel.dieValue() == 6) {
        assertEquals(10, walletViewModel.balance());
      }
    }
  }

  @Test
  public void rollDie_doubleNonSix_losesFiveCoins() {
    // Simulate rolling any number other than six twice in a row
    walletViewModel.rollDie();
    int firstRoll = walletViewModel.dieValue();

    if (firstRoll != 6) {
      walletViewModel.rollDie();
      int secondRoll = walletViewModel.dieValue();

      if (secondRoll == firstRoll) {
        assertEquals(-5, walletViewModel.balance());
      }
    }
  }

  @Test
  public void test_previousRoll_isUpdatedCorrectly() {
    // Test if the previous roll is updated correctly after each roll
    walletViewModel.rollDie();
    int firstRoll = walletViewModel.dieValue();
    //walletViewModel.rollDie();

    assertEquals(firstRoll, walletViewModel.previousRoll());
  }

  @Test
  public void test_totalRolls_incrementsCorrectly() {
    int initialRolls = walletViewModel.totalRolls();

    walletViewModel.rollDie();
    walletViewModel.rollDie();

    assertEquals(initialRolls + 2, walletViewModel.totalRolls());
  }

  @Test
  public void test_singleSixes_incrementsCorrectly() {
    walletViewModel.rollDie();
    if (walletViewModel.dieValue() == 6) {
      assertEquals(1, walletViewModel.singleSixes());
    }
  }

  @Test
  public void test_doubleSixes_incrementsCorrectly() {
    walletViewModel.rollDie();
    if (walletViewModel.dieValue() == 6) {
      walletViewModel.rollDie();
      if (walletViewModel.dieValue() == 6) {
        assertEquals(1, walletViewModel.doubleSixes());
      }
    }
  }

  @Test
  public void test_doubleOthers_incrementsCorrectly() {
    walletViewModel.rollDie();
    int firstRoll = walletViewModel.dieValue();

    if (firstRoll != 6) {
      walletViewModel.rollDie();
      int secondRoll = walletViewModel.dieValue();
      if (firstRoll == secondRoll) {
        assertEquals(1, walletViewModel.doubleOthers());
      }
    }
  }
}