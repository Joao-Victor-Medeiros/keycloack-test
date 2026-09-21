package bcn.deveight.keycloacktest.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Table(name = "pacientes")
@Entity(name = "Pacientes")
@Data
public class Pacient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Profile perfil;
    private String endereco;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
    private Double renda;
    private List<String> interesses;
    private List<String> condicaoAcesso;
    private String historicoSaude;
    private List<String> solicitacoesAtendimento;
}