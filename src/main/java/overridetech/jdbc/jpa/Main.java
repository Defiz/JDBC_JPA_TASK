package overridetech.jdbc.jpa;

import overridetech.jdbc.jpa.dao.UserDao;
import overridetech.jdbc.jpa.dao.UserDaoHibernateImpl;
import overridetech.jdbc.jpa.dao.UserDaoJDBCImpl;
import overridetech.jdbc.jpa.util.Util;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        UserDao userDao = new UserDaoHibernateImpl(util);
        userDao.createUsersTable();
        userDao.saveUser("Влад", "Костраш", (byte) 22);
        userDao.saveUser("Олег" ,"Сидоров", (byte) 30);
        userDao.saveUser("Глеб", "Жуков", (byte) 40);
        userDao.saveUser("Иван", "Пахниц", (byte) 3);
        userDao.getAllUsers();
        userDao.dropUsersTable();
        userDao.createUsersTable();
    }
}
