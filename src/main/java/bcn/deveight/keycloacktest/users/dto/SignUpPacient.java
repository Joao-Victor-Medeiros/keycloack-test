package bcn.deveight.keycloacktest.users.dto;

import bcn.deveight.keycloacktest.address.AddressData;
import bcn.deveight.keycloacktest.keycloack.dto.KeycloakUserDTO;
import bcn.deveight.keycloacktest.users.Profile;

import java.util.Date;
import java.util.List;

public record SignUpPacient(
        String username,
        String email,
        String password,
        Boolean enabled,
        Profile profile,
        AddressData address,
        Date birthday,
        Double income,
        List<String> concern,
        List<String> accessConditions,
        String healthHistory,
        List<String> serviceRequests
) {
    public KeycloakUserDTO toKeycloakSignUp() {
        return new KeycloakUserDTO(
                username,
                email,
                enabled,
                List.of(new KeycloakUserDTO.Credential(
                        "password",
                        password,
                        false
                ))
        );
    }
}
