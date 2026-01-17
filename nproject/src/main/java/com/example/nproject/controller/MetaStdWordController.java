package com.example.nproject.controller;

import com.example.nproject.dto.MetaStdWordDto;
import com.example.nproject.service.MetaStdWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/std-word")
public class MetaStdWordController {

    @Autowired
    private MetaStdWordService metaStdWordService;

    @GetMapping("/list")
    public String list(@ModelAttribute("searchDto") MetaStdWordDto searchDto, Model model) {
        // Default pagination
        if (searchDto.getPage() < 1) {
            searchDto.setPage(1);
        }
        if (searchDto.getSize() < 1) {
            searchDto.setSize(10);
        }

        List<MetaStdWordDto> list = metaStdWordService.getStdWordList(searchDto);
        int totalCount = metaStdWordService.getStdWordCount(searchDto);
        int totalPages = (int) Math.ceil((double) totalCount / searchDto.getSize());

        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", searchDto.getPage());

        return "std_word_list";
    }

    @GetMapping("/insert")
    public String insertForm(Model model) {
        model.addAttribute("stdWordDto", new MetaStdWordDto());
        return "std_word_insert";
    }

    @org.springframework.web.bind.annotation.PostMapping("/insert")
    public String insert(MetaStdWordDto stdWordDto, Model model) {
        String result = metaStdWordService.registerStdWord(stdWordDto);

        if ("DUPLICATE".equals(result)) {
            model.addAttribute("message", "중복된 단어가 있습니다.");
            model.addAttribute("searchUrl", "/std-word/insert");
            return "common/message"; // Assuming a common message view exists, or use script injection
        } else {
            model.addAttribute("message", "등록이 완료되었습니다.");
            model.addAttribute("searchUrl", "/std-word/list");
            return "common/message";
        }
    }

    @org.springframework.web.bind.annotation.PostMapping("/update")
    @org.springframework.web.bind.annotation.ResponseBody
    public String update(@org.springframework.web.bind.annotation.RequestBody MetaStdWordDto stdWordDto) {
        try {
            metaStdWordService.updateStdWord(stdWordDto);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    @GetMapping("/update")
    public String updateForm(@org.springframework.web.bind.annotation.RequestParam("wordId") Long wordId, Model model) {
        MetaStdWordDto stdWordDto = metaStdWordService.getStdWord(wordId);
        model.addAttribute("stdWordDto", stdWordDto);
        return "std_update"; // We will create this view
    }
}
