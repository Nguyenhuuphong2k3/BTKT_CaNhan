package BTKT.QLNS.QuanLyNhanSu.repository;

import BTKT.QLNS.QuanLyNhanSu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
