package bcn.deveight.keycloacktest.keycloack.dto;

import java.util.List;

public record KeycloakUserDTO(
        String username,
        String email,
        Boolean enabled,
        List<Credential> credentials
) {
    public static record Credential(
            String type,
            String value,
            Boolean temporary
    ) {}
}
