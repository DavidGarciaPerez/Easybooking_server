package server;

import java.rmi.Naming;

import services.ILoginService;
import services.IVueloService;
import services.LoginService;
import services.VueloService;

public class ServerManager {

	public static void main(String[] args) {
		if (args.length != 4) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String nameLogin = "//" + args[0] + ":" + args[1] + "/" + args[2];
		String nameVuelo = "//" + args[0] + ":" + args[1] + "/" + args[3];

		try {
			Server server = new Server();
			
			ILoginService loginService = new LoginService(server);			
			Naming.rebind(nameLogin, loginService);
			System.out.println("* Login Service '" + nameLogin + "' active and waiting...");
			
			IVueloService vueloService = new VueloService(server);
			Naming.rebind(nameVuelo, vueloService);
			System.out.println("* Vuelo Service '" + nameVuelo + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ ServerManager exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
