package com.malex;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class WeatherService {

  static final double KYIV_LATITUDE = 50.45;
  static final double KYIV_LONGITUDE = 30.52;

  static final double IVANO_FRK_LATITUDE = 48.92;
  static final double IVANO_FRK_LONGITUDE = 24.71;

  private static final Log log = LogFactory.getLog(WeatherService.class);

  @RestClient WeatherClient client;

  public WeatherResponse getWeather() {
    var kiev = client.getWeather(KYIV_LATITUDE, KYIV_LONGITUDE);
    var ivanoFrankivsk = client.getWeather(IVANO_FRK_LATITUDE, IVANO_FRK_LONGITUDE);
    var threadName = Thread.currentThread().getName();
    log.info("Thread name: %s".formatted(threadName));
    return new WeatherResponse(kiev, ivanoFrankivsk, threadName);
  }

  public record WeatherResponse(
      WeatherClient.WeatherClientResponse kiev,
      WeatherClient.WeatherClientResponse ivanoFrankivsk,
      String threadName) {}
}
