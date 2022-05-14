open module com.barretoareias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires javafx.graphics;
    requires lombok;

    //opens com.barretoareias.model.dao to java.sql;

    exports com.barretoareias;
    exports com.barretoareias.utils;
}
