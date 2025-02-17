package uz.pdp.railwayuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.railwayuserservice.entity.RoleEntity;
import uz.pdp.railwayuserservice.entity.enums.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(UserRole role);
}
