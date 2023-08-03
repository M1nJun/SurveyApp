/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jojun.survey_server.responses;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/responses")
@CrossOrigin(origins="*")
public class responsesController {
    private responsesDAO responsesDAO;
    
    public responsesController(responsesDAO dao) {
        this.responsesDAO = dao;
    }
    
    @PostMapping
    public String save(@RequestBody responses response){
        return "\"" + responsesDAO.save(response)+"\"";
    }
    
    @GetMapping()
    public List<Vote> responseCount() {
        return responsesDAO.getResponses();
    }
}
