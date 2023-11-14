package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mytest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "home";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.println("Connection OK");
        }catch (ClassNotFoundException |SQLException e){
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
    // реализуйте настройку соеденения с БД
    public static SessionFactory getSessionFactory(){
       if(sessionFactory == null) {
           Configuration configuration = new Configuration();
           Properties properties = new Properties();
           properties.put(Environment.DRIVER, DB_DRIVER);
           properties.put(Environment.URL, DB_URL);
           properties.put(Environment.USER, DB_USERNAME);
           properties.put(Environment.PASS, DB_PASSWORD);
           properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
           properties.put(Environment.SHOW_SQL, "true");
           properties.put(Environment.HBM2DDL_AUTO,"");

           configuration.setProperties(properties);
           configuration.addAnnotatedClass(User.class);
           ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                   .applySettings(configuration.getProperties()).build();
           sessionFactory = configuration.buildSessionFactory(serviceRegistry);
       }
       return sessionFactory;
    }
}
