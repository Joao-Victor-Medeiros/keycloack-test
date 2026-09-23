package bcn.deveight.keycloacktest.users;

import bcn.deveight.keycloacktest.address.Address;
import bcn.deveight.keycloacktest.address.AddressData;
import bcn.deveight.keycloacktest.keycloack.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacientService {
    private final KeycloakUserService keycloakUserService;
    private final PacientRepository pacientRepository;

    public PacientResponse register(SignUpPacient request) {
        validateDuplicity(request);

        UUID keycloakUserId = keycloakUserService.createUser(
                request.toKeycloakSignUp()
        );

        try {
            Pacient pacient = new Pacient();
            pacient.setId(keycloakUserId);
            pacient.setProfile(request.profile());
            pacient.setAddress(toAddress(request.address()));
            pacient.setBirthday(request.birthday());
            pacient.setIncome(request.income());
            pacient.setInteresses(request.concern());
            pacient.setHistoricoSaude(request.healthHistory());
            pacient.setSolicitacoesAtendimento(request.serviceRequests());

            return PacientResponse.from(pacientRepository.save(pacient));
        } catch (RuntimeException exception) {
            keycloakUserService.deleteUser(keycloakUserId);
            throw exception;
        }
    }

    private Address toAddress(AddressData address) {
        if (address == null) {
            return null;
        }

        return new Address(
                address.street(),
                address.neighborhood(),
                address.zipCode(),
                address.number(),
                address.complement(),
                address.city(),
                address.state()
        );
    }

    private void validateDuplicity(SignUpPacient request) {
        // A unicidade de username/e-mail deve ser validada também no Keycloak.
        // O banco local usa o UUID do Keycloak como chave primária.
    }
}
