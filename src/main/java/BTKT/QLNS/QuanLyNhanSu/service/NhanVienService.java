package BTKT.QLNS.QuanLyNhanSu.service;

import BTKT.QLNS.QuanLyNhanSu.model.NhanVien;
import BTKT.QLNS.QuanLyNhanSu.repository.NhanVienRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    // Retrieve all NhanVien from the database
    public List<NhanVien> getAllNhanViens() {
        return nhanVienRepository.findAll();
    }

    // Retrieve a NhanVien by its id
    public Optional<NhanVien> getNhanVienById(Long id) {
        return nhanVienRepository.findById(id);
    }

    // Add a new NhanVien to the database
    public NhanVien addNhanVien(NhanVien nhanvien) {
        return nhanVienRepository.save(nhanvien);
    }

    // Update an existing NhanVien
    public NhanVien updateNhanVien(@NotNull NhanVien nhanvien) {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanvien.getId())
                .orElseThrow(() -> new IllegalStateException("NhanVien with ID " +
                        nhanvien.getId() + " does not exist."));
        existingNhanVien.setName(nhanvien.getName());
        existingNhanVien.setImage(nhanvien.getImage());
        existingNhanVien.setNoiSinh(nhanvien.getNoiSinh());
        existingNhanVien.setPhongban(nhanvien.getPhongban());
        existingNhanVien.setLuong(nhanvien.getLuong());

        if(nhanvien.getImage() != null) {
            existingNhanVien.setImage(nhanvien.getImage());
        }
        return nhanVienRepository.save(existingNhanVien);
    }

    // Delete a NhanVien by its id
    public void deleteNhanVienById(Long id) {
        if (!nhanVienRepository.existsById(id)) {
            throw new IllegalStateException("NhanVien with ID " + id + " does not exist.");
        }
        nhanVienRepository.deleteById(id);
    }

    // Search NhanViens by name
    public List<NhanVien> searchNhanViensByName(String keyword) {
        return nhanVienRepository.findByNameContainingIgnoreCase(keyword);
    }
}
