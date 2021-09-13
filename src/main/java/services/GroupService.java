package services;

import DAO.GroupDAO;
import DAO.StudentDAO;
import models.GroupModel;
import models.StudentModel;

import java.util.List;


public class GroupService {

    private static final GroupDAO groupDAO = new GroupDAO();

    public GroupService() {
    }

    public static void update(GroupModel model) {
        groupDAO.update(model);
    }

    public static List<GroupModel> findAll() {
        return groupDAO.findAll();
    }

}
