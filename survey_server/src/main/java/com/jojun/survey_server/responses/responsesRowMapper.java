/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jojun.survey_server.responses;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jo
 */
public class responsesRowMapper implements RowMapper<responses> {

    @Override
    public responses mapRow(ResultSet rs, int rowNum) throws SQLException {
        responses r = new responses();
        r.setId(rs.getInt("id"));
        r.setSurvey(rs.getInt("survey"));
        r.setResponse(rs.getString("response"));
        return r;
    }
}
