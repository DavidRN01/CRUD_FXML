module com.mycompany.fxmllogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;

    opens com.mycompany.crudinterfaz to javafx.fxml, org.hibernate.orm.core, java.sql;
    opens models;
    exports com.mycompany.crudinterfaz;
}
