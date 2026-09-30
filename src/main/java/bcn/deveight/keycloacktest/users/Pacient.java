package bcn.deveight.keycloacktest.users;

import jakarta.persistence.Entity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "pacientes")
@Entity(name = "Pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pacient extends User{
    private Double income;
    @ElementCollection
    private List<String> interesses;
    private String historicoSaude;
    @ElementCollection
    private List<String> solicitacoesAtendimento;
}