package BTKT.QLNS.QuanLyNhanSu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhanviens")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên nhân viên là bắt buộc")
    @Column(nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(nullable = false)
    private String noiSinh;

    @ManyToOne
    @JoinColumn(name = "phongban_id", nullable = false)
    private PhongBan phongban;

    @Column(nullable = false)
    private int luong;
}
