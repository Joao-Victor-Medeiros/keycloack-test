package bcn.deveight.keycloacktest.users;

import bcn.deveight.keycloacktest.keycloack.KeycloakTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private KeycloakTokenService keycloakTokenService;

    @PostMapping("/signup-pacient")
    public ResponseEntity<String> signUpPacient(@RequestBody SignUpPacient signUpPacient) {
        // Convert SignUpPacient to Keycloack DTO
        SignUpPacient toKeycloackDto = signUpPacient;

        System.out.println(toKeycloackDto);
        // Prepare Keycloak payload
        var userPayload = Map.of(
                "username", signUpPacient.username(),
                "email", signUpPacient.email(),
                "enabled", signUpPacient.enabled(),
                "credentials", List.of(Map.of(
                        "type", "password",
                        "value", signUpPacient.password(),
                        "temporary", false
                ))
        );

        // Call KeycloakUserService to create user in Keycloak


        // Save Pacient to database (if needed, integrate with repository)
        // pacientRepository.save(pacient);

        return ResponseEntity.ok("User created successfully: " + toKeycloackDto);
    }
}
