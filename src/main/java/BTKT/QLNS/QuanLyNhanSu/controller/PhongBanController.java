package BTKT.QLNS.QuanLyNhanSu.controller;

import BTKT.QLNS.QuanLyNhanSu.model.PhongBan;
import BTKT.QLNS.QuanLyNhanSu.service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhongBanController {
    @Autowired
    private final PhongBanService phongBanService;

    @GetMapping("/phongbans/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongban", new PhongBan());
        return "/phongbans/add-phongban";
    }

    @PostMapping("/phongbans/add")
    public String addPhongBan(@Valid PhongBan phongban, BindingResult result) {
        if (result.hasErrors()) {
            return "/phongbans/add-phongban";
        }
        phongBanService.addPhongBan(phongban);
        return "redirect:/phongbans";
    }

    @GetMapping("/phongbans")
    public String listPhongBans(Model model) {
        List<PhongBan> phongbans = phongBanService.getAllPhongBans();
        model.addAttribute("phongbans", phongbans);
        return "/phongbans/phongban-list";
    }

    @GetMapping("/phongbans/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        PhongBan phongban = phongBanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid PhongBan Id:" + id));
        model.addAttribute("phongban", phongban);
        return "/phongbans/update-phongban";
    }

    @PostMapping("/phongbans/update/{id}")
    public String updatePhongBan(@PathVariable Long id, @Valid PhongBan phongban,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            phongban.setId(id);
            return "/phongbans/update-phongban";
        }
        phongBanService.updatePhongBan(phongban);
        model.addAttribute("phongbans", phongBanService.getAllPhongBans());
        return "redirect:/phongbans";
    }

    @GetMapping("/phongbans/delete/{id}")
    public String deletePhongBan(@PathVariable("id") Long id, Model model) {
        PhongBan phongban = phongBanService.getPhongBanById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid phongBan Id:" + id));
        phongBanService.deletePhongBanById(id);
        model.addAttribute("phongbans", phongBanService.getAllPhongBans());
        return "redirect:/phongbans";
    }
}
