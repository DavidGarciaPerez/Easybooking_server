package server;

import java.rmi.Naming;

import services.ILoginService;
import services.IPagoService;
import services.IVueloService;
import services.LoginService;
import services.PagoService;
import services.VueloService;

public class ServerManager {

	public static void main(String[] args) {
		if (args.length != 5) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String nameLogin = "//" + args[0] + ":" + args[1] + "/" + args[2];
		String nameVuelo = "//" + args[0] + ":" + args[1] + "/" + args[3];
		String namePago = "//" + args[0] + ":" + args[1] + "/" + args[4];

		try {
			Server server = new Server();
			
			ILoginService loginService = new LoginService(server);			
			Naming.rebind(nameLogin, loginService);
			System.out.println("* Login Service '" + nameLogin + "' active and waiting...");
			
			IVueloService vueloService = new VueloService(server);
			Naming.rebind(nameVuelo, vueloService);
			System.out.println("* Vuelo Service '" + nameVuelo + "' active and waiting...");
			
			IPagoService pagoService = new PagoService(server);
			Naming.rebind(namePago, pagoService);
			System.out.println("* Pago Service '" + nameVuelo + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ ServerManager exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
