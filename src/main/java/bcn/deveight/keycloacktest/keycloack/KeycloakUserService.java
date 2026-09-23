package bcn.deveight.keycloacktest.keycloack;

import bcn.deveight.keycloacktest.keycloack.dto.KeycloakUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.UUID;

@Service
public class KeycloakUserService {

    private final RestClient restClient;
    private final KeycloakTokenService tokenService;
    private final String realm;

    public KeycloakUserService(
            KeycloakTokenService tokenService,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.base-url}") String baseUrl
    ) {
        this.restClient = RestClient.create(baseUrl);
        this.tokenService = tokenService;
        this.realm = realm;
    }

    public UUID createUser(KeycloakUserDTO user) {
        URI location = restClient.post()
                .uri("/admin/realms/{realm}/users", realm)
                .headers(headers -> headers.setBearerAuth(tokenService.getAccessToken()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .exchange((request, response) -> {
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        throw new IllegalStateException(
                                "Falha ao criar usuário no Keycloak. Status: "
                                        + response.getStatusCode()
                        );
                    }
                    return response.getHeaders().getLocation();
                });

        if (location == null) {
            throw new IllegalStateException(
                    "O Keycloak criou o usuário, mas não retornou o header Location."
            );
        }

        return extractUserId(location);
    }

//    public void deleteUser(UUID userId) {
//        ResponseEntity<Void> response = restClient.delete()
//                .uri("/admin/realms/{realm}/users/{userId}", realm, userId)
//                .headers(headers -> headers.setBearerAuth(tokenService.getAccessToken()))
//                .retrieve()
//                .toBodilessEntity();
//
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new IllegalStateException(
//                    "Falha ao excluir usuário no Keycloak. Status: "
//                            + response.getStatusCode()
//            );
//        }
//    }

    private UUID extractUserId(URI location) {
        String path = location.getPath();
        String userId = path.substring(path.lastIndexOf('/') + 1);

        try {
            return UUID.fromString(userId);
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException(
                    "O Keycloak retornou um ID de usuário inválido no header Location.",
                    exception
            );
        }
    }
}
