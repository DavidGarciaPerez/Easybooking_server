package dao;

import java.util.List;

import data.Reserva;
import data.Usuario;

public interface IDAO {
	public boolean registerUser(Usuario usuario);
	public List<Usuario> getUsuarios(List<Usuario> arrayUsuarios);
	public List<Reserva> getReservas(List<Reserva> arrayReservas, Usuario usario);
	public boolean deleteUsuario(Usuario usuario);
	public boolean changeUserName(Usuario usuario);
}
