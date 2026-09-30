package bcn.deveight.keycloacktest.users.pacients;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacientRepository extends JpaRepository<Pacient, UUID> {
}