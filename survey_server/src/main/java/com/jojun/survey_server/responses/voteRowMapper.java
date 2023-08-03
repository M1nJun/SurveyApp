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
public class voteRowMapper implements RowMapper<Vote> {

    @Override
    public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vote v = new Vote();
        v.setAnswer(rs.getString("response"));
        v.setCount(rs.getInt("count(id)"));
        return v;
    }
}
