package com.api.sitie.controller;

import com.api.sitie.model.Usuario;
import com.api.sitie.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        String correoUsuario = request.get("correo");
        String contrasena = request.get("contrasena");

        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Login exitoso");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Sesión cerrada correctamente");
        return ResponseEntity.ok(response);
    }
}
