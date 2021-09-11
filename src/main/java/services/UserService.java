package services;

import DAO.UserDAO;
import models.UserModel;

import java.util.List;


    public class UserService {

        private static final UserDAO userDAO = new UserDAO();

        public UserService() {
        }

        public static void update(UserModel model) {
            userDAO.update(model);
        }

        public static List<UserModel> findAll() {
            return userDAO.findAll();
        }

    }
