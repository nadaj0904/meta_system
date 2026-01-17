package com.example.nproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/std-dashboard")
public class DashboardController {

    @org.springframework.beans.factory.annotation.Autowired
    private com.example.nproject.service.DashboardService dashboardService;

    @GetMapping
    public String dashboard(org.springframework.ui.Model model) {
        model.addAttribute("summary", dashboardService.getDashboardSummary());
        model.addAttribute("schemaTree", dashboardService.getSchemaTableTree());
        return "std_dashboard";
    }

    @GetMapping("/details")
    @org.springframework.web.bind.annotation.ResponseBody
    public com.example.nproject.dto.TableDetailDto getTableDetails(
            @org.springframework.web.bind.annotation.RequestParam("schema") String schema,
            @org.springframework.web.bind.annotation.RequestParam("table") String table) {
        return dashboardService.getTableDetails(schema, table);
    }
}
