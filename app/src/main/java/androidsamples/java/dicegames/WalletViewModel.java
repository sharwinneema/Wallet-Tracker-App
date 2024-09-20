package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

  /**
   * The no argument constructor.
   */
  private   int score;
  private   int number_of_sixes;
  private int number_of_double_sixes;
  private int number_of_double_others;
  private int previous_role;
  private Die6 die;
  private int total_roles;
  private boolean earnedCoins;
  private boolean lostCoin;

  public WalletViewModel() {
      this.score=0;
      this.number_of_double_others=0;
      this.number_of_double_sixes=0;
      this.number_of_sixes=0;
      this.previous_role=-1;
      this.die=new Die6();
      this.total_roles=totalRolls();
      this.earnedCoins=false;
      this.lostCoin=false;
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
      int currentRoll=this.die.value();
      total_roles++;
      if (currentRoll == 6) {
        this.lostCoin=false;
          // If the roll is 6, check if the previous roll was also 6
        if (this.previous_role == 6) {
          // Double sixes in a row
          this.score += 10;
          this.number_of_double_sixes++;
          this.earnedCoins=true;
        } else {
          // Single six
          this.score += 5;
          this.number_of_sixes++;
          this.earnedCoins=true;
        }
      } else {
        // For other numbers, check if it was rolled twice consecutively
          this.earnedCoins=false;
          if (currentRoll == this.previous_role) {
              this.score -= 5;// Lose 5 coins
              this.lostCoin=true;
              this.number_of_double_others++;
        }
          else this.lostCoin=false;
      }

      // Update the previous roll
      this.previous_role = currentRoll;
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   */
  public int dieValue() {
    int val =this.die.value();
    return  val;

  }

  /**
   * Reports the number of single (or first) sixes rolled so far.
   *
   */
  public int singleSixes() {
    // TODO implement method
    return this.number_of_sixes;
  }

  /**
   * Reports the total number of dice rolls so far.
   *
   */
  public int totalRolls() {
    // TODO implement method
    return this.total_roles;
  }

  /**
   * Reports the number of times two sixes were rolled in a row.
   *
   */
  public int doubleSixes() {
    return this.number_of_double_sixes;
  }

  /**
   * Reports the number of times any other number was rolled twice in a row.
   *
   */
  public int doubleOthers() {
    return this.number_of_double_others;
  }

  /**
   * Reports the value of the die on the previous roll.
   *
   */
  public int previousRoll() {
    return this.previous_role;
  }

  public  void  setpreviosRoll(int val){
        this.previous_role=val;
    }


    public void setBalance(int balance) {
        // Update the balance in ViewModel
        this.score = balance;
    }

    public void setDieValue(int value) {
        // Update the die value in ViewModel
        this.die.set_value(value);
    }

    public boolean hasEarnedCoins() {
        boolean result = earnedCoins;
        earnedCoins = false; // Reset flag after checking
        return result;
    }
    public boolean hasLossCoins() {
        boolean result = lostCoin;
        lostCoin = false; // Reset flag after checking
        return result;
    }


}
