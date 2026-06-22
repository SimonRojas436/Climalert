package ar.edu.utn.frba.ddsi.climalert.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherAPIResponse(
    LocationDTO location,
    CurrentDTO current
) {
  @JsonIgnoreProperties(ignoreUnknown = true)
  public record LocationDTO(
      @JsonProperty("name") String name,
      @JsonProperty("localtime") String localtime
  ) {}

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record CurrentDTO(
      @JsonProperty("temp_c") double tempC,
      double humidity,
      ConditionDTO condition
  ) {}

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record ConditionDTO(
      @JsonProperty("text") String text
  ) {}
}