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

        // 1. Conversão dos dados para o DTO do Keycloak.
        var keycloakUser = request.toKeycloakSignUp();

        // 2. Criação do usuário no Keycloak e 3. recuperação do UUID retornado.
        UUID keycloakUserId = keycloakUserService.createUser(keycloakUser);

        try {
            // 4. Montagem da entidade local usando o UUID do Keycloak como ID.
            Pacient pacient = new Pacient();
            pacient.setId(keycloakUserId);
            pacient.setProfile(request.profile());
            pacient.setAddress(toAddress(request.address()));
            pacient.setBirthday(request.birthday());
            pacient.setIncome(request.income());
            pacient.setInteresses(request.concern());
            pacient.setHistoricoSaude(request.healthHistory());
            pacient.setSolicitacoesAtendimento(request.serviceRequests());

            // 5. Persistência no banco local.
            Pacient savedPacient = pacientRepository.save(pacient);

            // 6. Retorno da resposta contendo o UUID do Keycloak.
            return PacientResponse.from(savedPacient);
        } catch (RuntimeException exception) {
            // A transação local não desfaz a chamada externa ao Keycloak.
            // Por isso, remove o usuário criado quando o save local falhar.
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
