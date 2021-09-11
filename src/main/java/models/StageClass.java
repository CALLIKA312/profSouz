package models;

import javafx.stage.Stage;

public class StageClass {

    private static Stage prStage;

    public static Stage getPrStage() {
        return prStage;
    }

    public static void setPrStage(Stage prStage) {
        StageClass.prStage = prStage;
    }

}
