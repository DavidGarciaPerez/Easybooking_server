package dao;

import java.util.List;

import data.Reserva;
import data.Usuario;

public interface IDAO {
	boolean registerUser(Usuario usuario);
	List<Usuario> getUsuarios(List<Usuario> arrayUsuarios);
	List<Reserva> getReservas(List<Reserva> arrayReservas, Usuario usario);
	boolean deleteUsuario(Usuario usuario);
	boolean changeUserName(Usuario usuario);
	boolean realizarReserva(Reserva reserva);
}
