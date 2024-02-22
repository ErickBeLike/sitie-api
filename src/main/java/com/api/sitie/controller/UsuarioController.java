package com.api.sitie.controller;


import com.api.sitie.exception.ResourceNotFoundException;
import com.api.sitie.model.Usuario;
import com.api.sitie.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable(value = "id") Long idUsuario) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el id ::" + idUsuario));
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/usuarios")
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable(value = "id") Long idUsuario, @RequestBody Usuario datosUsuario)
            throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el id :: " + idUsuario));

        usuario.setNombre_usuario(datosUsuario.getNombre_usuario());
        usuario.setCorreo(datosUsuario.getCorreo());
        usuario.setContrasena(datosUsuario.getContrasena());

        final Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    public Map<String, Boolean> eliminarUsuario(@PathVariable(value = "id") Long idUsuario) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el id :: " + idUsuario));

        usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }

}
