package BTKT.QLNS.QuanLyNhanSu.service;

import BTKT.QLNS.QuanLyNhanSu.model.PhongBan;
import BTKT.QLNS.QuanLyNhanSu.repository.PhongBanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;

    // Retrieve all PhongBan from the database
    public List<PhongBan> getAllPhongBans() {
        return phongBanRepository.findAll();
    }

    // Retrieve a PhongBan by its id
    public Optional<PhongBan> getPhongBanById(Long id) {
        return phongBanRepository.findById(id);
    }

    // Add a new PhongBan to the database
    public PhongBan addPhongBan(PhongBan phongban) {
        return phongBanRepository.save(phongban);
    }

    // Update an existing PhongBan
    public void updatePhongBan(@NotNull PhongBan phongban) {
        PhongBan existingPhongBan = phongBanRepository.findById(phongban.getId())
                .orElseThrow(() -> new IllegalStateException("PhongBan with ID " +
                        phongban.getId() + " does not exist."));
        existingPhongBan.setName(phongban.getName());
        phongBanRepository.save(existingPhongBan);
    }

    // Delete a PhongBan by its id
    public void deletePhongBanById(Long id) {
        if (!phongBanRepository.existsById(id)) {
            throw new IllegalStateException("PhongBan with ID " + id + " does not exist.");
        }
        phongBanRepository.deleteById(id);
    }
}
