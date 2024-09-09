package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

  /**
   * The no argument constructor.
   */
  private   int score;
  private   int number_of_sixes;
  private int number_of_double_sixes;
  private int number_of_double_roles;
  private int flag;
  private Die6 die;

  public WalletViewModel() {
      this.score=0;
      this.number_of_double_roles=0;
      this.number_of_double_sixes=0;
      this.number_of_sixes=0;
      this.flag=-1;
      this.die=new Die6();
  }

  /**
   * Reports the current balance.
   *
   */
  public int balance() {
    return score;
  }

  /**
   * Rolls the {@link Die} in the wallet and implements the changes accordingly.
   */
  public void rollDie() {
      die.roll();
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   */
  public int dieValue() {
    // TODO implement method
    return 0;
  }

  /**
   * Reports the number of single (or first) sixes rolled so far.
   *
   */
  public int singleSixes() {
    // TODO implement method
    return 0;
  }

  /**
   * Reports the total number of dice rolls so far.
   *
   */
  public int totalRolls() {
    // TODO implement method
    return 0;
  }

  /**
   * Reports the number of times two sixes were rolled in a row.
   *
   */
  public int doubleSixes() {
    // TODO implement method
    return 0;
  }

  /**
   * Reports the number of times any other number was rolled twice in a row.
   *
   */
  public int doubleOthers() {
    // TODO implement method
    return 0;
  }

  /**
   * Reports the value of the die on the previous roll.
   *
   */
  public int previousRoll() {
    // TODO implement method
    return 0;
  }
}
