package BTKT.QLNS.QuanLyNhanSu.repository;

import BTKT.QLNS.QuanLyNhanSu.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    List<NhanVien> findByNameContainingIgnoreCase(String keyword);
}
