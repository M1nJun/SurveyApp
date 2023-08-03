package com.jojun.survey_server.surveys;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class surveyDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public survey findSurvey() {
        String sql = "SELECT * FROM surveys WHERE date = CURDATE()";
        RowMapper<survey> rowMapper = new surveyRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }
    
}