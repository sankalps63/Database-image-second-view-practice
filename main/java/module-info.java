module com.example.jpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.jpro to javafx.fxml;
    exports com.example.jpro;
}