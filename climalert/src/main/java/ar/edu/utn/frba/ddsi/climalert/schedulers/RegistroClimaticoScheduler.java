package ar.edu.utn.frba.ddsi.climalert.schedulers;

import ar.edu.utn.frba.ddsi.climalert.services.RegistrosClimaticosService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RegistroClimaticoScheduler {
  private RegistrosClimaticosService registrosClimaticosService;

  public RegistroClimaticoScheduler(
      RegistrosClimaticosService registrosClimaticosService
  ) {
    this.registrosClimaticosService = registrosClimaticosService;
  }

  /*<---------------------------------------------------------------------------------------------------------------->*/

  // @Scheduled(initialDelay = 2000, fixedRate = 10000)
  @Scheduled(cron = "0 */5 * * * *")
  public void obtenerRegistroClimatico() {
    try {
      registrosClimaticosService.obtenerRegistroClimatico();
    } catch (Exception e) {
      System.out.println("[ERROR] - Hubo un error " + e.getMessage());
    }
  }

  // @Scheduled(initialDelay = 2000, fixedRate = 60000)
  @Scheduled(cron = "0 * * * * *")
  public void analizarUltimoRegistroClimatico() {
    try {
      registrosClimaticosService.analizarUltimoRegistroClimatico();
    } catch (Exception e) {
      System.out.println("[ERROR] - Hubo un error " + e.getMessage());
    }
  }
}
