package bcn.deveight.keycloacktest.users.dto;

import bcn.deveight.keycloacktest.users.pacients.Pacient;
import bcn.deveight.keycloacktest.users.Profile;

import java.util.UUID;

public record PacientResponse(
        UUID id,
        Profile profile,
        String status
) {
    public static PacientResponse from(Pacient pacient) {
        return new PacientResponse(pacient.getId(), pacient.getProfile(), "CREATED");
    }
}