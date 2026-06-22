package ar.edu.utn.frba.ddsi.climalert.repositories;

import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import java.util.Optional;

public interface RegistrosClimaticosRepository {

  RegistroClimatico save(RegistroClimatico entidadBeneficiaria);

  Optional<RegistroClimatico> findById(Long id);

  Optional<RegistroClimatico> findFirstByOrderByHoraDesc();

  void deleteById(Long id);
}
