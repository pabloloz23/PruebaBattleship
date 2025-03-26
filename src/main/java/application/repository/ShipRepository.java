package application.repository;

import application.model.Ship;
import application.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ShipRepository {
    public void save(Ship ship) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ship);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
