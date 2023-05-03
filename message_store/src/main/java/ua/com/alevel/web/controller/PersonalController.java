package ua.com.alevel.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.alevel.facade.PostFacade;
import ua.com.alevel.web.data.PersonalDashboardChartData;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    private final PostFacade postFacade;

    public PersonalController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @GetMapping
    public String index() {
        return "pages/user/personal/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pages/user/personal/dashboard";
    }

    @GetMapping("/dashboard/chart")
    public @ResponseBody ResponseEntity<PersonalDashboardChartData> generateChart() {
        return ResponseEntity.ok(postFacade.generatePersonalDashboardChartData());
    }
}
