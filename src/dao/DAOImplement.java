package dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import data.Pago;
import data.Reserva;
import data.Usuario;
import data.Vuelo;

public class DAOImplement implements IDAO {

	// Declaraci�n de variable datanucleusProperties:
	private String datanucleusProperties = "datanucleus.properties"; // JDO
	// Declaraci�n de variable clase: PersistenceManager.
	private PersistenceManager pm = null; // JDO
	// Declaraci�n de variable clase: Transaction.
	private Transaction tx = null; // JDO
	// Declaraci�n de variable clase: PersistenceManagerFactory: //JDO
	private PersistenceManagerFactory pmf;

	public static DAOImplement instance;

	public static DAOImplement getInstance() {
		if (instance == null) {
			instance = new DAOImplement();
		}

		return instance;
	}

	// Constructor para inicializar el PersistentManagerFactory:
	public DAOImplement() {
		this.pmf = JDOHelper.getPersistenceManagerFactory(datanucleusProperties);
	}

	@Override
	public boolean registerUser(Usuario usuario) {
		// TODO Auto-generated method stub
		boolean consultaCorrecta = false;
		try {
			// Get the Persistence Manager
			this.pm = this.pmf.getPersistenceManager();
			// Obtain the current transaction
			this.tx = this.pm.currentTransaction();
			// Start the transaction
			this.tx.begin();
			// Realizamos consulta a la BD:
			this.pm.makePersistent(usuario);
			// End the transaction
			this.tx.commit();
			// Confirmamos que la consulta se ha hecho correctamente.
			consultaCorrecta = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			if (pm != null && !pm.isClosed()) {
				pm.close();
				// ATTENTION - Datanucleus detects that the objects in memory were changed and
				// they are flushed to DB
			}
		}
		return consultaCorrecta;
	}

	@Override
	public List<Usuario> getUsuarios(List<Usuario> arrayUsuarios) {
		// TODO Auto-generated method stub
		try {
			// Get the Persistence Manager
			this.pm = this.pmf.getPersistenceManager();
			// Obtain the current transaction
			this.tx = this.pm.currentTransaction();
			// Start the transaction
			this.tx.begin();
			Query<Usuario> query = this.pm.newQuery(Usuario.class);
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) query.execute();
			// End the transaction
			this.tx.commit();
			// Comprobamos datos de los usuarios para ver si se han obtenido correctamente:
			for (Usuario usuario : usuarios) {
				// A�adimos los usuarios sacados de la bd a nuestra lista pasada como par�metro:
				arrayUsuarios.add(usuario);
				// Mostramos detalles de cada usuarios:
				System.out.println("NOMBRE: " + usuario.getNombre() + ",  EMAIL: " + usuario.getEmail()
						+ ",  SISTEMA_AUT:  " + usuario.getSistemaAutentificacion());
			}
		} catch (Exception ex) {

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
		return arrayUsuarios;
	}

	@Override
	public List<Reserva> getReservas(List<Reserva> arrayReservas, Usuario usario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean realizarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		boolean consultaCorrecta = false;
		try {
			// Get the Persistence Manager
			this.pm = this.pmf.getPersistenceManager();
			// Obtain the current transaction
			this.tx = this.pm.currentTransaction();
			// Start the transaction
			this.tx.begin();
			// Realizamos consulta a la BD:
			this.pm.makePersistent(reserva);
			// End the transaction
			this.tx.commit();
			// Confirmamos que la consulta se ha hecho correctamente.
			consultaCorrecta = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			if (pm != null && !pm.isClosed()) {
				pm.close();
				// ATTENTION - Datanucleus detects that the objects in memory were changed and
				// they are flushed to DB
			}
		}
		return consultaCorrecta;
	}

	@Override
	public boolean deleteUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		boolean consultaCorrecta = false;
		try {
			// Get the Persistence Manager
			this.pm = this.pmf.getPersistenceManager();
			// Obtain the current transaction
			this.tx = this.pm.currentTransaction();
			// Start the transaction
			this.tx.begin();

			// Preparac�n de la consulta:
			Query<Usuario> query = this.pm.newQuery(Usuario.class);

			// Para obtener todos los usuarios de la bd:
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) query.execute();

			// Para pasar el buscado en la bd (su identificativo) y pasarlo al
			// "usuarioActual":
			Usuario usuarioActual = null;

			// Buscamos usuario en la bd:
			for (Usuario user : usuarios) {
				// Recorremos usuarios para ver cual es el actual:
				if (user.getNombre().equals(usuario.getNombre()) && user.getEmail().equals(usuario.getEmail())) {
					// Lo tenemos:
					usuarioActual = user;
				}
			}

			// Una vez obtenido solo borramos ese:
			pm.deletePersistent(usuarioActual);

			// Confirmamos que se ha eliminado correctamente el usuario:
			consultaCorrecta = true;

			// End the transaction
			this.tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (this.tx != null && this.tx.isActive()) {
				this.tx.rollback();
			}

			if (this.pm != null && !this.pm.isClosed()) {
				this.pm.close();
			}
		}
		return consultaCorrecta;
	}

	@Override
	public boolean changeUserName(Usuario usuario) {
		// TODO Auto-generated method stub
		boolean consultaCorrecta = false;
		try {
			// Get the Persistence Manager
			this.pm = this.pmf.getPersistenceManager();
			// Obtain the current transaction
			this.tx = this.pm.currentTransaction();
			// Start the transaction
			this.tx.begin();
			// Hacemos persistencia a la bd:
			pm.makePersistent(usuario);
			// Confirmamos que se ha modificado el usuario:
			consultaCorrecta = true;

			// End the transaction
			this.tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (this.tx != null && this.tx.isActive()) {
				this.tx.rollback();
			}

			if (this.pm != null && !this.pm.isClosed()) {
				this.pm.close();
			}
		}
		return consultaCorrecta;
	}

}
