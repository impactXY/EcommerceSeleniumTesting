module com.example.seleniummaven1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires dev.failsafe.core;
    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.chrome_driver;
    requires org.testng;
    requires org.seleniumhq.selenium.support;

    opens com.example.tests to javafx.fxml;
    exports com.example.tests;
    exports com.example.setup;
    opens com.example.setup to javafx.fxml;
}