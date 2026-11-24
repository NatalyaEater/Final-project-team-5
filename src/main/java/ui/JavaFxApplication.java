//JavaFxApplication.java — класс, наследующий Application.
// В методе start создаёт MainWindow и показывает его. Это мост между запуском JavaFX и окном.

package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        MainWindow mainWindow = new MainWindow(primaryStage);
        mainWindow.show();
    }
}
