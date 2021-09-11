package services;

import DAO.StudentDAO;
import DAO.UserDAO;
import models.StudentModel;
import models.UserModel;

import java.util.List;


    public class StudentService {

        private static final StudentDAO studentDAO = new StudentDAO();

        public StudentService() {
        }

        public static void update(StudentModel model) {
            studentDAO.update(model);
        }

        public static List<StudentModel> findAll() {
            return studentDAO.findAll();
        }

    }
