package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "faculty", schema = "public")
public class FacultyModel {
    @Id
    private String facultyName;

    public FacultyModel() {
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "FacultyModel{" +
                "facultyName='" + facultyName + '\'' +
                '}';
    }
}
