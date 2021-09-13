package DAO;

import models.FacultyModel;
import models.GroupModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class FacultyDAO {

    public FacultyDAO() {
    }

    public void update(FacultyModel test) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(test);
        tx1.commit();
        session.close();
    }

    public static List<FacultyModel> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List list = session.createQuery("From GroupModel").list();
        tx1.commit();
        session.close();
        return list;
    }
}

