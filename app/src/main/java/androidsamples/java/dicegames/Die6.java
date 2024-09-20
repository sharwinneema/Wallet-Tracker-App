package androidsamples.java.dicegames;

import java.util.Random;

/**
 * An implementation of a six-faced {@link Die} using {@link Random}.
 */
public class Die6 implements Die {
  private  int value;
  public Die6() {
    this.value=0;
  }

  @Override
  public void roll() {
    Random random= new Random();
    this.value=random.nextInt(6) + 1;
  }

  @Override
  public int value() {
    // TODO implement method
    return this.value;
  }

  public void set_value(int val){
      this.value=val;
  }
}
