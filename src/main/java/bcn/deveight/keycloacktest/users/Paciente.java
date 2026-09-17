package bcn.deveight.keycloacktest.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class Paciente {
    @Id
    private Long id;
    private String perfil;
    private String endereco;
    private Date dataNascimento;
    private Double renda;
    private List<String> interesses;
    private List<String> condicaoAcesso;
    private String historicoSaude;
    private List<String> solicitacoesAtendimento;
}