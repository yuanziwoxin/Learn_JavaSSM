package com.yuan.entity;

import java.util.Date;

public class Selection {

    private int studentId;
    private int courseId;
    private Date selection_time;
    private int score;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getSelection_time() {
        return selection_time;
    }

    public void setSelection_time(Date selection_time) {
        this.selection_time = selection_time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
