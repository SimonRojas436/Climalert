package ar.edu.utn.frba.ddsi.climalert.adapters.impl;

import ar.edu.utn.frba.ddsi.climalert.adapters.ProveedorClimaAdapter;
import ar.edu.utn.frba.ddsi.climalert.dtos.WeatherAPIResponse;
import ar.edu.utn.frba.ddsi.climalert.mappers.RegistroClimaticoMapper;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherAPIProveedorClima implements ProveedorClimaAdapter {
  private RegistroClimaticoMapper registroClimaticoMapper;
  private final RestTemplate restTemplate;
  private final String apiUrl;
  private final String apiKey;
  private final String location;

  public WeatherAPIProveedorClima(
      RegistroClimaticoMapper registroClimaticoMapper,
      RestTemplate restTemplate,
      @Value("${weatherapi.url}") String apiUrl,
      @Value("${weatherapi.key}") String apiKey,
      @Value("${weatherapi.location}") String location
  ) {
    this.registroClimaticoMapper = registroClimaticoMapper;
    this.restTemplate = restTemplate;
    this.apiUrl = apiUrl;
    this.apiKey = apiKey;
    this.location = location;
  }

  @Override
  public RegistroClimatico obtenerClimaActual() {
    String url = UriComponentsBuilder.fromUriString(apiUrl)
        .queryParam("key", apiKey)
        .queryParam("q", location)
        .toUriString();
    WeatherAPIResponse weatherAPIResponse = restTemplate.getForObject(url, WeatherAPIResponse.class);

    if (weatherAPIResponse == null || weatherAPIResponse.location() == null) {
      throw new RuntimeException("Error en la API");
    }

    return registroClimaticoMapper.toEntity(weatherAPIResponse);
  }
}
