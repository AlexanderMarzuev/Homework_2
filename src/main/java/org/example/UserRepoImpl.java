package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLOutput;
import java.util.List;

public class UserRepoImpl implements UserRepo {
    @Override
    public User create(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();

            return user;
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Пользователь не сохранен");
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public User update(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User findUser = session.find(User.class, user.getId());

            if (findUser != null) {
                findUser.setName(user.getName());
                findUser.setEmail(user.getEmail());
                findUser.setAge(user.getAge());
            } else {
                System.out.println("Пользователь по идентификатору не найден: " + user.getId());
            }
            transaction.commit();

            return user;

        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Идентификатор пользователя не обновлен: " + user.getId());
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public User findId(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            User user = session.find(User.class, id);
            transaction.commit();
            if (user == null) {
                System.out.println("Пользователь с идентификатором " + id + " не найден.");
            } else {
                System.out.println("Идентификатор" + user.getId() + ", Имя=" + user.getName());
            }

            return user;

        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Найти не удалось.");
                transaction.rollback();
            }
            throw e;
        }
    }


    @Override
    public List<User> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            var query = session.createQuery("SELECT u From User u", User.class);
            List<User> users = query.getResultList();
            transaction.commit();
            System.out.println("Поиск пользователей: " + users);
            return users;

        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Найти не удалось.");
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);

            if (user != null) {
                session.remove(user);
            } else {
                System.out.println("Пользователь не найден по идентификатору: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Транзакция отменена. Ошибка удаления из-за идентификатора пользователя: " + id);
                transaction.rollback();
            }
            throw e;
        }
    }
}
