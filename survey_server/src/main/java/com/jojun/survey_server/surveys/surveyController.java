
package com.jojun.survey_server.surveys;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins="*")
public class surveyController {
    private surveyDAO surveyDAO;
    
    public surveyController(surveyDAO dao){
        this.surveyDAO = dao;
    }
    
    @GetMapping()
    public survey surveyInfo() {
        return surveyDAO.findSurvey();
    }
}
