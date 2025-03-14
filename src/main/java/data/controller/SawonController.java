package data.controller;

import data.dto.SawonDto;
import data.service.SawonService;
import naver.storage.NcpObjectStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class SawonController {
    @Autowired
    SawonService sawonService;
    @Autowired
    NcpObjectStorageService storageService;

    private String imagePath = "https://kr.object.ncloudstorage.com/bitcamp-bucket/sawon/";
    private String bucketName = "bitcamp-bucket";

    @GetMapping({"/"})
    public String main() {
        return "main";
    }

    @GetMapping({"/list"})
    public String sawonList(Model model) {
        List<SawonDto> list = sawonService.getSelectAllSawon();
        model.addAttribute("list", list);
        model.addAttribute("totalCount", list.size());
        model.addAttribute("imagePath", imagePath);

        return "sawon/sawonlist";
    }

    @GetMapping({"/form"})
    public String sawonForm() {
        return "sawon/sawonform";
    }

    @PostMapping("/insert")
    public String sawonInsert(@ModelAttribute SawonDto sawonDto,
                              @RequestParam(value = "upload", required = false) MultipartFile upload) {
        if (upload != null) {
            String photo = storageService.uploadFile(bucketName, "sawon", upload);
            sawonDto.setPhoto(photo);
        }
        sawonService.insertSawon(sawonDto);

        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String deleteSawon(@RequestParam(value = "num") int num) {
        SawonDto sawonDto = sawonService.getSawon(num);
        storageService.deleteFile(bucketName, "sawon", sawonDto.getPhoto());
        sawonService.deleteSawon(num);
        return "redirect:/list";
    }

    @GetMapping("/detail")
    public String detailSawon(@RequestParam(value = "num") int num, Model model) {
        SawonDto sawonDto = sawonService.getSawon(num);
        model.addAttribute("dto", sawonDto);
        model.addAttribute("imagePath", imagePath);
        return "sawon/sawondetail";
    }

    @GetMapping("/updateform")
    public String updateForm(@RequestParam(value = "num") int num, Model model) {
        SawonDto sawonDto = sawonService.getSawon(num);
        model.addAttribute("dto", sawonDto);
        model.addAttribute("imagePath", imagePath);
        return "sawon/sawonupdateform";
    }

    @PostMapping("/update")
    public String updateSawon(@RequestParam int num,
                              @ModelAttribute SawonDto sawonDto,
                              @RequestParam(value = "upload", required = false) MultipartFile upload) {
        if (upload != null) {
            storageService.deleteFile(bucketName, "sawon", sawonService.getSawon(num).getPhoto());
            String photo = storageService.uploadFile(bucketName, "sawon", upload);
            sawonDto.setPhoto(photo);
        }
        sawonDto.setNum(num);
        sawonService.updateSawon(sawonDto);

        return "redirect:/detail?num="+num;
    }
}