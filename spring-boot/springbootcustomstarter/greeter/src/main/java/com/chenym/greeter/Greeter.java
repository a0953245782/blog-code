package com.chenym.greeter;

import static com.chenym.greeter.GreeterConfigParams.AFTERNOON_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.EVENING_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.MORNING_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.NIGHT_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.USER_NAME;

import java.time.LocalDateTime;

/**
 * Created by chenym on 2018/6/28.
 */
public class Greeter {

  private GreetingConfig greetingConfig;

  public Greeter(GreetingConfig greetingConfig) {
    this.greetingConfig = greetingConfig;
  }

  public String greet(LocalDateTime localDateTime) {
    String name = greetingConfig.getProperty(USER_NAME);
    int hourOfDay = localDateTime.getHour();

    if (hourOfDay >= 5 && hourOfDay < 12) {
      return String.format("Hello %s, %s", name, greetingConfig.get(MORNING_MESSAGE));
    } else if (hourOfDay >= 12 && hourOfDay < 17) {
      return String.format("Hello %s, %s", name, greetingConfig.get(AFTERNOON_MESSAGE));
    } else if (hourOfDay >= 17 && hourOfDay < 20) {
      return String.format("Hello %s, %s", name, greetingConfig.get(EVENING_MESSAGE));
    } else {
      return String.format("Hello %s, %s", name, greetingConfig.get(NIGHT_MESSAGE));
    }
  }

  public String greet() {
    return greet(LocalDateTime.now());
  }
}
