package ar.edu.utn.frba.ddsi.climalert.mappers;

import ar.edu.utn.frba.ddsi.climalert.dtos.WeatherAPIResponse;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroClimaticoMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "ubicacion", source = "location.name")
  @Mapping(target = "hora", source = "location.localtime")
  @Mapping(target = "temperaturaC", source = "current.tempC")
  @Mapping(target = "humedad", source = "current.humidity")
  @Mapping(target = "condicion", source = "current.condition.text")
  RegistroClimatico toEntity(WeatherAPIResponse apiResponse);
}
