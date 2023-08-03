package com.jojun.survey_server.surveys;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class surveyRowMapper implements RowMapper<survey> {

    @Override
    public survey mapRow(ResultSet row, int rowNum) throws SQLException {
        survey s = new survey();
        s.setId(row.getInt("id"));
        s.setDate(row.getDate("date"));
        s.setQuestion(row.getString("question"));
        s.setOption(row.getString("options"));

        String[] options = s.getOption().split(",");
        s.setOption1(options[0]);
        s.setOption2(options[1]);
        s.setOption3(options[2]);
        s.setOption4(options[3]);

        return s;
    }
}
