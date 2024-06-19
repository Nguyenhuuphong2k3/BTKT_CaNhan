package BTKT.QLNS.QuanLyNhanSu.repository;

import BTKT.QLNS.QuanLyNhanSu.model.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {
}
