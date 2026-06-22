package ar.edu.utn.frba.ddsi.climalert.adapters;

public interface NotificadorEmailAdapter {

  void enviarEmail(String email, String asunto, String cuerpo);
}
