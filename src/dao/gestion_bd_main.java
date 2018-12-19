package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import data.Aerolinea;
import data.Aeropuerto;
import data.Usuario;
import data.Vuelo;

public class gestion_bd_main {

	// Declaración de variable datanucleusProperties:
	private String datanucleusProperties = "datanucleus.properties"; // JDO
	// Declaración de variable clase: PersistenceManager.
	private PersistenceManager pm = null; // JDO
	// Declaración de variable clase: Transaction.
	private Transaction tx = null; // JDO
	// Declaración de variable clase: PersistenceManagerFactory: //JDO
	private PersistenceManagerFactory pmf;

	public gestion_bd_main() {
		this.pmf = JDOHelper.getPersistenceManagerFactory(datanucleusProperties);
	}

	@SuppressWarnings("unused")
	private boolean registerUser(Usuario usuario) {
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

	private List<Usuario> getUsuarios(List<Usuario> arrayUsuarios) {
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
				// Añadimos los usuarios sacados de la bd a nuestra lista pasada como parámetro:
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

	private boolean deleteUsuario(Usuario usuario) {
		boolean consultaCorrecta = false;
		try {
			// Get the Persistence Manager
			this.pm = this.pmf.getPersistenceManager();
			// Obtain the current transaction
			this.tx = this.pm.currentTransaction();
			// Start the transaction
			this.tx.begin();

			// Preparacón de la consulta:
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

	public boolean realizarReserva(Vuelo vuelo) {
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
			this.pm.makePersistent(vuelo);
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

	private boolean changeUserName(Usuario usuario) {
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

	public static void main(String args[]) {
		// Instanciamos clase de prueba para la gestión de bd:
		gestion_bd_main gbd = new gestion_bd_main();

		// Creamos un nuevo usuario para comprobar la persistencia de un nuevo usuario
		// registrado en la bd:
		Usuario davidUser = new Usuario("David García", "david.g.p@opendeusto.es", "Google");
		Usuario aitorUser = new Usuario("Aitor Martinez", "aitor.martinez@opendeusto.es", "Facebook");
		// Hacemos persistencia de davidUser y aitorUser:
		if (gbd.registerUser(davidUser) != true) {
			System.out.print("Error al hacer la consulta");
		}
		if (gbd.registerUser(aitorUser) != true) {
			System.out.println("Error al hacer la consulta");
		}

		// Hacemos consulta a la bd para obtener todos los usuarios:
		List<Usuario> arrayUsuarios = new ArrayList<Usuario>();
		arrayUsuarios = gbd.getUsuarios(arrayUsuarios);

		// Modificación del nombre de un usuario:
		// Ahora modificamos:
		Usuario newUser = new Usuario("Nuevo_nombre", arrayUsuarios.get(0).getEmail(),
				arrayUsuarios.get(0).getSistemaAutentificacion());
		if (gbd.changeUserName(newUser) != true) {
			System.out.println("Error al modificar el nombre del usuario: " + arrayUsuarios.get(0).getNombre());
		}

		//Aeropuerto ao = new Aeropuerto("Bilbao Aeropuerto", "Bilbao");
		//Aeropuerto ad = new Aeropuerto("Donosti Aeropuerto", "Donosti");
		//Aerolinea aerolinea = new Aerolinea("VUELING");
		//Vuelo vuelo = new Vuelo(1000, new Date(), new Date(), 100, 50, ao, ad, aerolinea);
		//gbd.realizarReserva(vuelo);

		// Borramos todos los usuarios de la bd:
		arrayUsuarios = new ArrayList<Usuario>();
		arrayUsuarios = gbd.getUsuarios(arrayUsuarios);
		for (Usuario usuario : arrayUsuarios) {
			// Eliminamos un usuario de la bd: tiene que existir antes
			// Ahora lo eliminamos:
			if (gbd.deleteUsuario(usuario) != true) {
				System.out.println("Error al eliminar el usuario: " + usuario.getNombre());
			}
		}
	}

}
