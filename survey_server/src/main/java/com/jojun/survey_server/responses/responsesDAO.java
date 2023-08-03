/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jojun.survey_server.responses;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class responsesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String save(responses response) {
        String insertSQL = "INSERT INTO responses(survey,response) values (?, ?)";
        jdbcTemplate.update(insertSQL, response.getSurvey(), response.getResponse());
        return "success";
    }

    public List<Vote> getResponses() {
        List<Vote> votes = new ArrayList();

        String getSurvey = "SELECT id FROM surveys where date = CURDATE()";
        int surveyID = jdbcTemplate.queryForObject(getSurvey, Integer.class);

        String allResponses = "SELECT count(id), response FROM responses where survey = " + surveyID +" GROUP BY response";

        RowMapper<Vote> rowMapper = new voteRowMapper();
        votes = jdbcTemplate.query(allResponses, rowMapper);

        
        return votes;
    }
}
