package controllers;

import DAO.FacultyDAO;
import DAO.GroupDAO;
import DAO.StudentDAO;
import DAO.UserDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import models.FacultyModel;
import models.GroupModel;
import models.StudentModel;
import models.UserModel;
import org.apache.regexp.RE;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AdminController {

    public ScrollPane scrollPane;

    public ComboBox<String> groupComboBox;

    public Label fio_label;
    public Label student_code;
    public boolean firstLoad = true;

    public List<UserModel> userModelList = new ArrayList<>();
    public List<StudentModel> studentModelList = new ArrayList<>();
    public List<FacultyModel> facultyModelList = new ArrayList<>();
    public List<GroupModel> groupModelList = new ArrayList<>();

    public List<File> files = new ArrayList<>();
    public String photosPath;

    @FXML
    void initialize() throws MalformedURLException {


        scrollPane.getStylesheets().add("views/mainCSS.css");
        userModelList = UserDAO.findAll();

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Вход");
        dialog.setHeaderText("Введите логин и пароль");

        ButtonType loginButtonType = new ButtonType("Войти", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField loginField = new TextField();
        loginField.setPromptText("Логин");

        loginField.setText("admin");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");

        grid.add(new Label("Логин: "), 0, 0);
        grid.add(loginField, 1, 0);
        grid.add(new Label("Пароль:"), 0, 1);
        grid.add(passwordField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(passwordField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(loginField.getText(), passwordField.getText());
            }
            return null;
        });

        AtomicReference<String> usernameAtomic = new AtomicReference<>("");
        AtomicReference<String> passwordAtomic = new AtomicReference<>("");
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            usernameAtomic.set(usernamePassword.getKey());
            passwordAtomic.set(usernamePassword.getValue());
        });

        String username = usernameAtomic.get();
        String password = passwordAtomic.get();

        List<String> usernameList = new ArrayList<>();
        List<String> passwordList = new ArrayList<>();
        for (UserModel userModel : userModelList) {
            usernameList.add(userModel.getUsername());
            passwordList.add(userModel.getPassword());
        }

        if (username.isEmpty() && password.isEmpty()) {

            return;
        }


        if (username.equals("admin")) {
            // if (passwordList.get(passwordList.indexOf(username)).equals(password)) {
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ТЫ АДМИН!");
            alert.showAndWait();*/
        }

        if (usernameList.contains(username)) {
            if (!passwordList.get(usernameList.indexOf(username)).equals(password)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Неверно введен пароль!");
                alert.showAndWait();
                initialize();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Нет пользователя с таким именем");
            alert.showAndWait();
            initialize();
        }


        studentModelList = StudentDAO.findAll();
        facultyModelList = FacultyDAO.findAll();
        groupModelList = GroupDAO.findAll();

        groupComboBox.getItems().add("");
        for (GroupModel groupModel : groupModelList) groupComboBox.getItems().add(groupModel.getGroupCode());


        File folder = new File(System.getProperty("user.dir"));

        files = Arrays.asList(folder.listFiles());
        photosPath = folder.getAbsolutePath() + '\\' + "photos";

        folder = files.get(files.indexOf(new File(photosPath)));
        files = Arrays.asList(folder.listFiles());


        scrollPane.setContent(refreshStudentsList());
    }


    public VBox refreshStudentsList() throws MalformedURLException {

        VBox vBox = new VBox();

        List<StudentModel> filteredStudentModelList = studentModelList;
        if (firstLoad) {
            firstLoad = false;
        } else {
            if (!groupComboBox.getValue().isEmpty()) {
                filteredStudentModelList = studentModelList.stream()
                        .filter(rec -> rec.getGroupCode().equals(groupComboBox.getValue()))
                        .collect(Collectors.toList());
            }
        }

        for (StudentModel studentModel : filteredStudentModelList) {

            HBox hBox = new HBox();

            File file = files.get(files.indexOf(new File(photosPath + '\\' + studentModel.getStudentCode() + ".jpg")));
            Image image = new Image(String.valueOf(file.toURI().toURL()));
            ImageView studentImage = new ImageView(image);
            studentImage.setFitHeight(75);
            studentImage.setFitWidth(75);
            hBox.getChildren().add(studentImage);

            hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            String studCode = (((Label) (hBox.getChildren().get(1))).getText());
                            studCode = studCode.substring(studCode.lastIndexOf("Номер зачетной книжки: ") + "Номер зачетной книжки: ".length());


                            GridPane grid = new GridPane();
                            grid.setHgap(10);
                            grid.setVgap(10);
                            grid.setPadding(new Insets(20, 10, 10, 10));

                            HBox studentHBox = new HBox();
                            ImageView studentImageView = new ImageView();
                            studentHBox.getChildren().add(studentImageView);
                            VBox studentVBox = new VBox();
                            Label fioLabel = new Label();
                            Label groupAndFacultyLabel = new Label();
                            Label numberLabel = new Label();

                            studentModelList = StudentDAO.findAll();
                            String finalStudCode = studCode;
                            StudentModel curStudent = studentModelList.stream()
                                    .filter(rec -> rec.getStudentCode().equals(finalStudCode))
                                    .collect(Collectors.toList()).get(0);

                            File folder = new File(System.getProperty("user.dir"));
                            String photosPath = folder.getAbsolutePath() + '\\' + "photos";

                            List<File> files = Arrays.asList(folder.listFiles());
                            folder = files.get(files.indexOf(new File(photosPath)));
                            files = Arrays.asList(folder.listFiles());

                            File file = files.get(files.indexOf(new File(photosPath + '\\' + curStudent.getStudentCode() + ".jpg")));
                            Image image = null;
                            try {
                                image = new Image(String.valueOf(file.toURI().toURL()));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            studentImageView.setImage(image);
                            studentImageView.setFitHeight(320);
                            studentImageView.setFitWidth(180);

                            String studentGroup = curStudent.getGroupCode();
                            fioLabel.setText("ФИО: " + curStudent.getSurName() + ' ' + curStudent.getName() + ' ' + curStudent.getSecondName() + '\n' +
                                    "Номер зачетной книжки: " + curStudent.getStudentCode());
                            groupAndFacultyLabel.setText("Группа: " + studentGroup + '\n' +
                                    "Факультет: " + groupModelList.get(studentGroup.indexOf(studentGroup)).getFacultyName());
                            numberLabel.setText("Номер телефона: " + curStudent.getPhoneNumber());
                            studentVBox.getChildren().addAll(fioLabel, groupAndFacultyLabel, numberLabel);
                            studentVBox.setPadding(new Insets(5));
                            studentHBox.getChildren().add(studentVBox);
                            Button editButton = new Button();
                            try {
                                File file1 = new File(photosPath + '\\' + "edit.png");
                                file1 = files.get(files.indexOf(file1));
                                Image image1 = new Image(String.valueOf(file1.toURI().toURL()));
                                ImageView imageView = new ImageView(image1);
                                imageView.setFitWidth(35);
                                imageView.setFitHeight(35);
                                imageView.setOnMouseClicked(event -> {
                                    //TODO добавить редактирование пользователя
                                });
                                editButton.setGraphic(imageView);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }

                            studentHBox.getChildren().add(editButton);
                            VBox viewVBox = new VBox();
                            viewVBox.getChildren().addAll(studentHBox);
                            grid.add(viewVBox, 0, 0);

                            Stage stage = new Stage();
                            stage.setTitle("Студент " + curStudent.getSurName() + ' ' + curStudent.getName() + ' ' + curStudent.getSecondName());
                            stage.setScene(new Scene(grid));
                            stage.showAndWait();
                        }
                    }
                }
            });

            Label fioAndCodeLabel = new Label();
            fioAndCodeLabel.setPadding(new Insets(2, 2, 2, 2));
            fioAndCodeLabel.setText("ФИО: " + studentModel.getSurName() + ' ' + studentModel.getName() + ' ' + studentModel.getSecondName() + '\n' +
                    "Номер зачетной книжки: " + studentModel.getStudentCode());
            hBox.getChildren().add(fioAndCodeLabel);

            String group = studentModel.getGroupCode();
            Label groupAndFacultyLabel = new Label();
            groupAndFacultyLabel.setPadding(new Insets(2, 2, 2, 2));
            /*
            groupAndFacultyLabel.setText("Номер группы: " + studentModel.getGroupCode() + '\n' +
                    "Факультет: " + groupModelList.get(group.indexOf(group)).getFacultyName());*/
            hBox.getChildren().add(groupAndFacultyLabel);
            vBox.getChildren().add(hBox);
        }

        return vBox;
    }


    public void groupFilterChoose(ActionEvent actionEvent) throws MalformedURLException {
        scrollPane.setContent(refreshStudentsList());
    }
}
