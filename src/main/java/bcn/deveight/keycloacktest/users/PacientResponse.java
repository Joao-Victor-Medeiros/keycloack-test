package bcn.deveight.keycloacktest.users;

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