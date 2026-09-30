package bcn.deveight.keycloacktest.users;

import bcn.deveight.keycloacktest.users.dto.PacientResponse;
import bcn.deveight.keycloacktest.users.dto.SignUpPacient;
import bcn.deveight.keycloacktest.users.pacients.PacientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final PacientService pacientService;

    public UserController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @PostMapping("/signup-pacient")
    public ResponseEntity<PacientResponse> signUpPacient(@RequestBody SignUpPacient signUpPacient) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pacientService.register(signUpPacient));
    }
}
