package controllers;

import DAO.StudentDAO;
import DAO.UserDAO;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import models.StudentModel;
import models.UserModel;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class UserController {

    @FXML
    private String studentCode;

    public Label fioLabel;
    public Label groupLabel;
    public ImageView imageView;

    public List<UserModel> userModelList = new ArrayList<>();
    public List<StudentModel> studentModelList = new ArrayList<>();


    @FXML
    void initialize() throws MalformedURLException {

        userModelList = UserDAO.findAll();

        List<String> usernameList = new ArrayList<>();
        List<String> passwordList = new ArrayList<>();
        for (UserModel userModel : userModelList) {
            usernameList.add(userModel.getUsername());
            passwordList.add(userModel.getPassword());
        }

        String username = "user";
        studentModelList = StudentDAO.findAll();
        StudentModel curStudent = studentModelList.stream()
                .filter(rec -> rec.getStudentCode().equals(studentModelList.get(usernameList.indexOf(username)).getStudentCode()))
                .collect(Collectors.toList()).get(0);

        File folder = new File(System.getProperty("user.dir"));
        String photosPath = folder.getAbsolutePath() + '\\' + "photos";

        List<File> files = Arrays.asList(folder.listFiles());
        folder = files.get(files.indexOf(new File(photosPath)));
        files = Arrays.asList(folder.listFiles());

        File file = files.get(files.indexOf(new File(photosPath + '\\' + curStudent.getStudentCode() + ".jpg")));
        Image image = new Image(String.valueOf(file.toURI().toURL()));
        imageView.setImage(image);

        fioLabel.setText("ФИО " + curStudent.getSurName() + ' ' + curStudent.getName() + ' ' + curStudent.getSecondName() + '\n' +
                "Номер зачетной книжки " + curStudent.getStudentCode());
        groupLabel.setText(curStudent.getGroupCode());

    }

    void initData(String studentCode){
        this.studentCode = studentCode;

    }


}
