package ru.tama.administrateuser.repository.impl;

import ru.tama.administrateuser.entity.User;
import ru.tama.administrateuser.repository.api.UserRepository;
import ru.tama.administrateuser.repository.util.HibernateUtil;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class {@code UserRepositoryImpl} implements work with database through Hibernate {@link Session}.
 * Used logger slf4j.
 *
 * @see Session
 * @see UserRepository
 * @author tama
 */
public class UserReposirotyImpl implements UserRepository {
    Logger logger = LoggerFactory.getLogger(UserReposirotyImpl.class);

    /**
     * {@inheritDoc}
     */
    public User createUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        logger.info("createUser in repository start");

        session.beginTransaction();
        Integer idUser = (Integer)session.save(user);
        User userResult = session.load(User.class, idUser);
        session.getTransaction().commit();

        logger.info("createUser in repository end");
        return userResult;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        logger.info("deleteUser in repository start");

        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();

        logger.info("deleteUser in repository end");
    }

    /**
     * {@inheritDoc}
     */
    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        logger.info("updateUser in repository start");

        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

        logger.info("updateUser in repository end");
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getAllUser() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        logger.info("getAllUser in repository start");

        session.beginTransaction();
        List<User> listUser = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();

        logger.info("getAllUser in repository end");
        return listUser;
    }

    /**
     * {@inheritDoc}
     */
    public User getUserById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        logger.info("getUserById in repository start - id: " + id);

        session.beginTransaction();
        User user = session.load(User.class, id);
        session.getTransaction().commit();

        logger.info("getUserById in repository end");
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public List<User> findByLogin(String login) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        logger.info("findByLogin in repository start - login: " + login);

        session.beginTransaction();
        List<User> listUser = session.createQuery(
                String.format("from User where login = '%s'", login), User.class).list();
        session.getTransaction().commit();

        logger.info("findByLogin in repository end");
        return listUser;
    }
}
