package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "student", schema = "public")
public class StudentModel {
    @Id
    private String studentCode;

    @Column
    private String name;

    @Column
    private String secondName;

    @Column
    private String surName;

    @Column
    private int curse;

    @Column
    private String groupCode;

    public StudentModel() {
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getCurse() {
        return curse;
    }

    public void setCurse(int curse) {
        this.curse = curse;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentCode='" + studentCode + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surName='" + surName + '\'' +
                ", curse=" + curse +
                ", groupCode='" + groupCode + '\'' +
                '}';
    }
}
