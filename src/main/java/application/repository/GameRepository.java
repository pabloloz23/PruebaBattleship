package application.repository;

import application.model.Game;
import application.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GameRepository {
    public void save(Game game) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(game);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
