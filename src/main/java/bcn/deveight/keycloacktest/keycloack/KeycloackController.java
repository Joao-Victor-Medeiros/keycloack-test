package bcn.deveight.keycloacktest.keycloack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeycloackController {
    private final KeycloakTokenService tokenService;

    public KeycloackController(KeycloakTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/teste/keycloak/token")
    public String token() {
        return tokenService.getAccessToken();
    }
}