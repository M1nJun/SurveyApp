/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jojun.survey_server.responses;

public class Vote {
    public String answer;
    public int count;
    
    public Vote(){
        
    }
    
    public Vote(String answer, int count) {
        this.answer = answer;
        this.count = count;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
