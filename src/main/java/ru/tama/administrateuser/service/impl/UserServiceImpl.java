package ru.tama.administrateuser.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tama.administrateuser.entity.User;
import ru.tama.administrateuser.repository.RepositoryFactory;
import ru.tama.administrateuser.repository.api.UserRepository;
import ru.tama.administrateuser.service.api.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Class {@code UserServiceImpl} implements work with {@link UserRepository} and {@link UserService} interface.
 * Used Spring annotation @Service.
 * Used logger slf4j.
 *
 * @author tama
 */
@Service
public class UserServiceImpl implements UserService {
    private RepositoryFactory factory = RepositoryFactory.getRepositoryFactory();
    private UserRepository userRepository = factory.getUserRepository();
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    /**
     * {@inheritDoc}
     */
    public User getUser(Integer idUser) {
        logger.info("getUser in service start - id: " + idUser);

        User user = userRepository.getUserById(idUser);

        logger.info("getUser in service end - login: " + user.getLogin());
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public boolean createUserIfNotExists(User user) {
        logger.info("createUserIfNotExists in service start - login: " + user.getLogin());

        List<User> userList = userRepository.findByLogin(user.getLogin());
        if (userList.size() != 0) {
            logger.info("createUserIfNotExists in service end - false");
            return false;
        }

        user = userRepository.createUser(user);
        if (user == null) {
            logger.info("createUserIfNotExists in service FAILED - false");
            return false;
        }

        logger.info("createUserIfNotExists in service end - true");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateUserIfExists(User user) {
        logger.info("updateUserIfExists in service start - id: " + user.getId());

        User userResult = userRepository.getUserById(user.getId());
        if (userResult == null) {
            logger.info("updateUserIfExists in service end - false");
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(user.getDateBirthday());
        user.setDateBirthday(calendar.getTime());

        userRepository.updateUser(user);

        logger.info("updateUserIfExists in service end - true");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteUserIfExists(Integer id) {
        logger.info("deleteUserIfExists in service start - id: " + id);

        User userResult = userRepository.getUserById(id);
        if (userResult == null) {
            logger.info("deleteUserIfExists in service end - false");
            return false;
        }

        userRepository.deleteUser(userResult);

        logger.info("deleteUserIfExists in service end - true");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isUserExist(Integer idUser) {
        logger.info("isUserExist in service start - id: " + idUser);

        User userResult = userRepository.getUserById(idUser);
        if (userResult == null) {
            logger.info("isUserExist in service end - false");
            return false;
        }

        logger.info("isUserExist in service end - true");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getAllUser() {
        logger.info("getAllUser in service start");

        List<User> userList = userRepository.getAllUser();
        if(userList.size() == 0) {
            logger.info("getAllUser in service end - NULL");
            return null;
        }

        logger.info("getAllUser in service end");
        return userList;
    }

    /**
     * {@inheritDoc}
     */
    public User findUserByLogin(String login) {
        logger.info("findUserByLogin in service start - login: " + login);

        List<User> userList = userRepository.findByLogin(login);
        if (userList.size() != 1) {
            logger.info("findUserByLogin in service end - NULL");
            return null;
        }

        logger.info("findUserByLogin in service end");
        return userList.get(0);
    }
}
