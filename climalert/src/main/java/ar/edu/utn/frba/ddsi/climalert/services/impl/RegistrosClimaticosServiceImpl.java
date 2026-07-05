package ar.edu.utn.frba.ddsi.climalert.services.impl;

import ar.edu.utn.frba.ddsi.climalert.adapters.NotificadorEmailAdapter;
import ar.edu.utn.frba.ddsi.climalert.adapters.ProveedorClimaAdapter;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import ar.edu.utn.frba.ddsi.climalert.repositories.RegistrosClimaticosRepository;
import ar.edu.utn.frba.ddsi.climalert.services.RegistrosClimaticosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegistrosClimaticosServiceImpl implements RegistrosClimaticosService {
  private RegistrosClimaticosRepository registrosClimaticosRepository;
  private ProveedorClimaAdapter proveedorClimaAdapter;
  private NotificadorEmailAdapter notificadorEmailAdapter;
  @Value("${climalert.alertas.destinatarios}")
  private List<String> correosInteresados;

  public RegistrosClimaticosServiceImpl(
      RegistrosClimaticosRepository registrosClimaticosRepository,
      ProveedorClimaAdapter proveedorClimaAdapter,
      NotificadorEmailAdapter notificadorEmailAdapter
  ) {
    this.registrosClimaticosRepository = registrosClimaticosRepository;
    this.proveedorClimaAdapter = proveedorClimaAdapter;
    this.notificadorEmailAdapter = notificadorEmailAdapter;
  }

  /*<---------------------------------------------------------------------------------------------------------------->*/

  @Override
  public void obtenerRegistroClimatico() {
    RegistroClimatico registroClimatico = proveedorClimaAdapter.obtenerClimaActual();
    RegistroClimatico registroGuardado = registrosClimaticosRepository.save(registroClimatico);
  }

  public void analizarUltimoRegistroClimatico() {
    RegistroClimatico ultimoRegistroClimatico = registrosClimaticosRepository.findFirstByOrderByHoraDesc()
        .orElseThrow(() -> new RuntimeException("No hay registros climáticos disponibles"));

    if (ultimoRegistroClimatico.esCritico()) {
      String asunto = "Alerta climática: " + ultimoRegistroClimatico.getHora();

      String cuerpo = String.format("""
          Hola,
          se ha detectado una condición climática crítica.
            - Ubicación: %s
            - Temperatura: %.1f °C
            - Humedad: %.1f %%
          Por favor, tome las precauciones necesarias.
          
          Climalert
          """, ultimoRegistroClimatico.getUbicacion(),
          ultimoRegistroClimatico.getTemperaturaC(),
          ultimoRegistroClimatico.getHumedad());

      correosInteresados.forEach(c -> notificadorEmailAdapter.enviarEmail(c, asunto, cuerpo));
    }
  }
}
