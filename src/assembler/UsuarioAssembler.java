package assembler;

import java.util.ArrayList;
import java.util.List;

import data.Usuario;
import data.dto.UsuarioDTO;

public class UsuarioAssembler {

	public List<UsuarioDTO> assemble(List<Usuario> usuarios) {
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();

		for (Usuario a : usuarios) {
			usuariosDTO.add(new UsuarioDTO(a.getNombre(),a.getEmail(),a.getSistemaAutentificacion()));
		}
		
		return usuariosDTO;
	}
	
	public UsuarioDTO assembleOne(Usuario usuario) {
		return new UsuarioDTO(usuario.getNombre(),usuario.getEmail(),usuario.getSistemaAutentificacion());
	}
	
}
