package ar.edu.utn.frba.ddsi.climalert.models.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RegistroClimatico {
  @Setter
  private Long id;
  private String ubicacion;
  private String hora;
  private double temperaturaC;
  private double humedad;
  private String condicion;

  public RegistroClimatico(
      String ubicacion,
      String hora,
      double temperaturaC,
      double humedad,
      String condicion
  ) {
    this.id = null;
    this.ubicacion = ubicacion;
    this.hora = hora;
    this.temperaturaC = temperaturaC;
    this.humedad = humedad;
    this.condicion = condicion;
  }

  /*<---------------------------------------------------------------------------------------------------------------->*/

  public boolean esCritico() {
    return this.temperaturaC > 35 || this.humedad > 60;
  }
}
