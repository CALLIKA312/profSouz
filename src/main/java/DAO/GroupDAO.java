package DAO;

import models.GroupModel;
import models.StudentModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class GroupDAO {

    public GroupDAO() {
    }

    public void update(GroupModel test) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(test);
        tx1.commit();
        session.close();
    }

    public static List<GroupModel> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List list = session.createQuery("From GroupModel").list();
        tx1.commit();
        session.close();
        return list;
    }
}

