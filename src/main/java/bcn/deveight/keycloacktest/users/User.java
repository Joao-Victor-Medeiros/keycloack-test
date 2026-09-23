package bcn.deveight.keycloacktest.users;

import bcn.deveight.keycloacktest.address.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Profile profile;
    @Embedded
    private Address address;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
}
