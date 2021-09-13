package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "group", schema = "public")
public class GroupModel {
    @Id
    private String groupCode;

    @Column
    private String facultyName;

    @Column
    private String groupName;

    public GroupModel() {
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "GroupModel{" +
                "groupCode='" + groupCode + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
