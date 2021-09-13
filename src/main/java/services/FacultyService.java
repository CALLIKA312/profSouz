package services;

import DAO.FacultyDAO;
import DAO.GroupDAO;
import models.FacultyModel;
import models.GroupModel;

import java.util.List;


public class FacultyService {

    private static final FacultyDAO facultyDAO = new FacultyDAO();

    public FacultyService() {
    }

    public static void update(FacultyModel model) {
        facultyDAO.update(model);
    }

    public static List<FacultyModel> findAll() {
        return facultyDAO.findAll();
    }

}
