package ar.edu.utn.frba.ddsi.climalert.adapters;

import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;

public interface ProveedorClimaAdapter {

  RegistroClimatico obtenerClimaActual();
}
