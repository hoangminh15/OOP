module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;

    opens View to javafx.fxml;
    exports View;

}