module com.mycompany.storemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.surveyfxml to javafx.fxml;
    exports com.mycompany.surveyfxml;
}
