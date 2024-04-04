package teamchallenge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import teamchallenge.server.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(String name);
}
