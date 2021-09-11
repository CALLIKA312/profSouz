package controllers;

import DAO.UserDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class MainController {

    public List<UserModel> userModelList = new ArrayList<>();


    @FXML
    void initialize() {

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


        if (username.equals("admin")) {
            if (passwordList.get(passwordList.indexOf(username)).equals(password)) {
                //*TODO* Открытие админки
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("ТЫ АДМИН!");
                alert.showAndWait();


            }
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


    }
}
