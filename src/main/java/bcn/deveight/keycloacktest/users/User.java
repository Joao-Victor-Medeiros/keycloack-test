package bcn.deveight.keycloacktest.users;

import bcn.deveight.keycloacktest.address.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Profile profile;
    @Embedded
    private Address address;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
}
