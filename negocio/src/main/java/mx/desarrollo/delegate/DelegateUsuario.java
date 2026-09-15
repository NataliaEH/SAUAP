package mx.desarrollo.delegate;

import mx.desarrollo.entity.Usuario;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateUsuario {
    public Usuario iniciarSesion(String usuario, String contrasena){
        Usuario usuarioEncontrado = new Usuario();
        List<Usuario> usuarios = ServiceLocator.getInstanceUsuarioDAO().findAll();

        for(Usuario us:usuarios){
            if(us.getContrasena().equalsIgnoreCase(contrasena) && us.getCorreo().equalsIgnoreCase(usuario)){
                usuarioEncontrado = us;
            }
        }
        return usuarioEncontrado;
    }
