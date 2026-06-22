package ar.edu.utn.frba.ddsi.climalert.repositories.inMemory;

import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import ar.edu.utn.frba.ddsi.climalert.repositories.RegistrosClimaticosRepository;
import ar.edu.utn.frba.ddsi.climalert.utils.GeneradorIdSecuencial;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class InMemoryRegistrosClimaticos implements RegistrosClimaticosRepository {
  private final List<RegistroClimatico> registrosClimaticos = new ArrayList<>();
  private final GeneradorIdSecuencial generadorIdSecuencial = new GeneradorIdSecuencial();

  /*<---------------------------------------------------------------------------------------------------------------->*/

  @Override
  public RegistroClimatico save(RegistroClimatico eegistroClimatico) {
    if (eegistroClimatico.getId() == null) {
      eegistroClimatico.setId(generadorIdSecuencial.siguiente());
      registrosClimaticos.add(eegistroClimatico);
      return eegistroClimatico;
    }
    deleteById(eegistroClimatico.getId());
    registrosClimaticos.add(eegistroClimatico);
    return eegistroClimatico;
  }

  @Override
  public Optional<RegistroClimatico> findById(Long id) {
    return registrosClimaticos.stream()
        .filter(n -> n.getId().equals(id))
        .findFirst();
  }

  @Override
  public Optional<RegistroClimatico> findFirstByOrderByHoraDesc() {
    return registrosClimaticos.stream()
        .max(Comparator.comparing(RegistroClimatico::getHora));
  }

  @Override
  public void deleteById(Long id) {
    registrosClimaticos.removeIf(d -> d.getId().equals(id));
  }
}
