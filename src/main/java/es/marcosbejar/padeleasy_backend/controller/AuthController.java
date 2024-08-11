package es.marcosbejar.padeleasy_backend.controller;

import es.marcosbejar.padeleasy_backend.exception.PadeleasyException;
import es.marcosbejar.padeleasy_backend.model.Role;
import es.marcosbejar.padeleasy_backend.model.User;
import es.marcosbejar.padeleasy_backend.objects.UserLoginObject;
import es.marcosbejar.padeleasy_backend.objects.UserRegisterObject;
import es.marcosbejar.padeleasy_backend.security.JwtResponse;
import es.marcosbejar.padeleasy_backend.service.RoleService;
import es.marcosbejar.padeleasy_backend.service.UserService;
import es.marcosbejar.padeleasy_backend.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600, allowCredentials = "true")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginObject loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            UserDetails userdet = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userdet.getUsername());
            User user = userService.findByUsername(userdet.getUsername());
            Long userID = user.getId();
            Set<Role> roles = user.getRoles();
            String reftok = jwtUtil.generateRefToken(userdet.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, reftok, userID, roles));
        }catch (UsernameNotFoundException e) {
            // Maneja el caso en que el usuario no se encuentra
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } catch (BadCredentialsException e) {
            // Maneja el caso en que las credenciales son incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } catch (AuthenticationException e) {
            // Maneja otras excepciones de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
        } catch (Exception e) {
            // Maneja cualquier otra excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({PadeleasyException.class})
    @Transactional
    public ResponseEntity<String> register(@RequestBody UserRegisterObject user) {
        try {
            // Verifica si el usuario ya existe
            if (userService.findByUsername(user.getUsername()) != null) {
              return ResponseEntity.ok("User already exists");
            }

            // Registra al nuevo usuario
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir durante el registro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("user/{username}")
    public ResponseEntity<User> getUserProfile(@PathVariable String username) {
        User userProfile = userService.findByUsername(username);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getRoles() {
        return roleService.findAllExceptAdmin();
    }
}
