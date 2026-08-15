package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties prop = new Properties();
                // Загружаем properties из classpath
                try (InputStream input = HibernateUtil.class.getClassLoader()
                        .getResourceAsStream("database.properties")) {
                    if (input == null) {
                        throw new RuntimeException("Файл database.properties не найден");
                    }
                    prop.load(input);
                }

                sessionFactory = new Configuration()
                        .addProperties(prop)
                        .addAnnotatedClass(User.class) 
                        .buildSessionFactory();
            } catch (IOException e) {
                throw new RuntimeException("Ошибка загрузки конфигурации", e);
            }
        }
        return sessionFactory;
    }
}
