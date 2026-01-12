package overridetech.jdbc.jpa.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Util util;

    public UserDaoHibernateImpl() {
    }

    public UserDaoHibernateImpl(Util util) {
        this.util = util;
    }


    @Override
    public void createUsersTable() {
        Session session = util.getSession();
        Transaction tx = session.beginTransaction();
        session.createNativeQuery("""
                    CREATE TABLE IF NOT EXISTS users (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(50),
                        last_name VARCHAR(50),
                        age SMALLINT)
                """).executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = util.getSession();
        Transaction tx = session.beginTransaction();
        session.createNativeQuery("""
                            DROP TABLE IF EXISTS users
                """).executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = util.getSession();
        Transaction tx = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tx.commit();
        session.close();
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = util.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(session.get(User.class, id));
        tx.commit();
       session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = util.getSession();
        Query<User> query = session.createQuery("FROM User", User.class);
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = util.getSession();
        Transaction tx = session.beginTransaction();
        session.createNativeQuery("""
                TRUNCATE TABLE IF EXISTS users
                """);
        tx.commit();
        session.close();
    }
}
