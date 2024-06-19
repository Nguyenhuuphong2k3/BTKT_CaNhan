package BTKT.QLNS.QuanLyNhanSu.controller;

import BTKT.QLNS.QuanLyNhanSu.model.NhanVien;
import BTKT.QLNS.QuanLyNhanSu.service.NhanVienService;
import BTKT.QLNS.QuanLyNhanSu.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/nhanviens")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String showNhanVienList(@RequestParam(value = "keyword", required = false)String keyword, Model model) {
        List<NhanVien> nhanviens;
        if (keyword != null && !keyword.isEmpty()) {
            nhanviens = nhanVienService.searchNhanViensByName(keyword);
        } else {
            nhanviens = nhanVienService.getAllNhanViens();
        }
        model.addAttribute("nhanviens", nhanviens);
        model.addAttribute("keyword", keyword);
        return "/nhanviens/nhanvien-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongbans", phongBanService.getAllPhongBans());
        return "/nhanviens/add-nhanvien";
    }

    @PostMapping("/add")
    public String addNhanVien(@Valid @ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult result, @RequestParam("imageFile") MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "/nhanviens/add-nhanvien";
        }

        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath + fileName);
                Files.write(path, imageFile.getBytes());
                nhanvien.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "/nhanviens/add-nhanvien";
            }
        }
        nhanVienService.addNhanVien(nhanvien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhanVien nhanvien = nhanVienService.getNhanVienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid NhanVien Id:" + id));
        model.addAttribute("nhanvien", nhanvien);
        model.addAttribute("phongbans", phongBanService.getAllPhongBans());

        return "/nhanviens/update-nhanvien";
    }

    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable Long id, @Valid @ModelAttribute("nhanvien") NhanVien nhanvien,
                                 BindingResult result, @RequestParam("imageFile") MultipartFile imageFile) {
        if (result.hasErrors()) {
            nhanvien.setId(id);
            return "/nhanviens/update-nhanvien";
        }
        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath + fileName);
                Files.write(path, imageFile.getBytes());
                nhanvien.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "/nhanviens/update-nhanvien";
            }
        }
        nhanVienService.updateNhanVien(nhanvien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable Long id) {
        nhanVienService.deleteNhanVienById(id);
        return "redirect:/nhanviens";
    }
}
