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
}
