package application.repository;

import application.model.Board;
import application.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BoardRepository {
    public void save(Board board) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(board);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
