package application.repository;

import application.model.Player;
import application.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerRepository {
    public void save(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
