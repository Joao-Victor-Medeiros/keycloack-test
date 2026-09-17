package bcn.deveight.keycloacktest.keycloack;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeycloakTokenService {
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public KeycloakTokenService(
            OAuth2AuthorizedClientManager authorizedClientManager
    ) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getAccessToken() {

        var principal =
                new UsernamePasswordAuthenticationToken(
                        "api-domain-dev",
                        "N/A",
                        Collections.emptyList()
                );

        OAuth2AuthorizeRequest authorizeRequest =
                OAuth2AuthorizeRequest
                        .withClientRegistrationId("keycloak")
                        .principal(principal)
                        .build();

        OAuth2AuthorizedClient authorizedClient =
                authorizedClientManager.authorize(authorizeRequest);

        if (authorizedClient == null) {
            throw new IllegalStateException(
                    "Não foi possível obter token do Keycloak."
            );
        }

        return authorizedClient
                .getAccessToken()
                .getTokenValue();
    }
}
