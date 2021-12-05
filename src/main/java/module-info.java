module reggieplanner{
    requires com.opencsv;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    opens com.it326;
    requires java.base;
    exports com.it326;
    exports com.it326.Majors;

}
