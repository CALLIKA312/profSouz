package DAO;

import models.StudentModel;
import models.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class StudentDAO {

    public StudentDAO() {
    }

    public void update(StudentModel test) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(test);
        tx1.commit();
        session.close();
    }

    public static List<StudentModel> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List list = session.createQuery("From StudentModel").list();
        tx1.commit();
        session.close();
        return list;
    }
}

