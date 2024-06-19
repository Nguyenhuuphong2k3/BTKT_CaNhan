package BTKT.QLNS.QuanLyNhanSu.repository;

import BTKT.QLNS.QuanLyNhanSu.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{
    Role findRoleById(Long id);
}
