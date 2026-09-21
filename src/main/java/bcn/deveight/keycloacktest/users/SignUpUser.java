package bcn.deveight.keycloacktest.users;

import bcn.deveight.keycloacktest.address.AddressData;

import java.util.Date;
import java.util.List;

public record SignUpUser(
        Profile profile,
        AddressData address,
        Date birthday,
        Double income,
        List<String> concern,
        List<String> condicaoAcesso,
        String historicoSaude,
        List<String> solicitacoesAtendimento
) {}
