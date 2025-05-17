package com.malex;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.rest.client.reactive.ClientQueryParam;
import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

/*
 * If you're using current_weather=true, the response can include:
 *
 * temperature (°C)
 * windspeed (km/h)
 * winddirection (°)
 * weathercode (icon code for weather condition)
 * is_day (1 = day, 0 = night)
 * time (timestamp of the observation)
 */
@RegisterRestClient(baseUri = "https://api.open-meteo.com/v1/forecast?current_weather=true")
public interface WeatherClient {

  @GET
  @ClientQueryParam(name = "current_weather", value = "true")
  WeatherClientResponse getWeather(@RestQuery double latitude, @RestQuery double longitude);

  /*
   * Represents the weather client response
   */
  record WeatherClientResponse(@JsonProperty("current_weather") Weather weather) {}

  /*
   * Represents the inner json object
   *
   * root obj: current_weather
   * sub:
   * - temperature: 14.3
   * - windspeed": 11.2
   * - winddirection: 74
   * - weathercode: 3
   * - is_day: 1
   * - time: "2025-05-17T12:00" - ISO 8601
   */
  record Weather(
      double temperature, // e.g., 21.5
      double windspeed, // e.g., 11.3
      double winddirection,
      int weathercode, // e.g., 3 (partly cloudy)
      int is_day, // 1 = day, 0 = nights
      String time // e.g., "2025-05-17T10:00"
      ) {}
}
