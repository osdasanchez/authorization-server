package mx.osdasanchez.authorizationserver.repository;

import mx.osdasanchez.authorizationserver.entity.Role;
import mx.osdasanchez.authorizationserver.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(RoleName roleName);
}