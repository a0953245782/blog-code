package com.chenym.greeter.autoconfigure;

import static com.chenym.greeter.GreeterConfigParams.AFTERNOON_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.EVENING_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.MORNING_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.NIGHT_MESSAGE;
import static com.chenym.greeter.GreeterConfigParams.USER_NAME;

import com.chenym.greeter.Greeter;
import com.chenym.greeter.GreetingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenym on 2018/6/28.
 */
@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {

  @Autowired
  private GreeterProperties greeterProperties;

  @Bean
  @ConditionalOnMissingBean
  public GreetingConfig greeterConfig() {
    String userName = greeterProperties.getUserName() == null ? System.getProperty("user.name")
        : greeterProperties.getUserName();
    String morningMessage = greeterProperties.getMorningMessage() == null ? "Good Morning"
        : greeterProperties.getMorningMessage();
    String afternoonMessage = greeterProperties.getAfternoonMessage() == null ? "Good Afternoon"
        : greeterProperties.getAfternoonMessage();
    String eveningMessage = greeterProperties.getEveningMessage() == null ? "Good Evening"
        : greeterProperties.getEveningMessage();
    String nightMessage = greeterProperties.getNightMessage() == null ? "Good Night"
        : greeterProperties.getNightMessage();

    GreetingConfig greetingConfig = new GreetingConfig();
    greetingConfig.put(USER_NAME, userName);
    greetingConfig.put(MORNING_MESSAGE, morningMessage);
    greetingConfig.put(AFTERNOON_MESSAGE, afternoonMessage);
    greetingConfig.put(EVENING_MESSAGE, eveningMessage);
    greetingConfig.put(NIGHT_MESSAGE, nightMessage);
    return greetingConfig;
  }

  @Bean
  @ConditionalOnMissingBean
  public Greeter greeter(GreetingConfig greetingConfig) {
    return new Greeter(greetingConfig);
  }

}