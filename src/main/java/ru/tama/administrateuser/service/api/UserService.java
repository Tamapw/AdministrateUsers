package ru.tama.administrateuser.service.api;


import ru.tama.administrateuser.entity.User;
import ru.tama.administrateuser.repository.api.UserRepository;

import java.util.List;

/**
 * Interface {@code UserService} implements business logic application.
 * Create, update, delete, get users and checks them.
 * Used methods from interface {@link UserRepository}
 *
 * @author tama
 */
public interface UserService {
    /**
     * Gets user by id from database and checks if it exists.
     *
     * @param idUser This id is in the database. By this id the getting {@link User} from the database.
     * @return The {@link User} who was getted by the given id or null if it not exists.
     */
    User getUser(Integer idUser);

    /**
     * Create {@link User} in database and set id in param user.
     *
     * @param user the {@link User} that need to be created.
     * @return true if user created, false if user by login exists.
     */
    boolean createUserIfNotExists(User user);


    /**
     * Update {@link User} in database.
     *
     * @param user the {@link User} that need to be updated.
     * @return true if user updated, false if user by id not exists.
     */
    boolean updateUserIfExists(User user);


    /**
     * Delete {@link User} by id from database.
     *
     * @param id the id of{@link User} that need to be deleted.
     * @return true if user deleted, false if user by id not exists.
     */
    boolean deleteUserIfExists(Integer id);

    /**
     * Check if the {@link User} by idUser exists in database.
     *
     * @param idUser This id is in the database. By this id the checks {@link User} in the database.
     * @return true if {@link User} exists, false if user not exists.
     */
    boolean isUserExist(Integer idUser);

    /**
     * Get all database users and return list of {@link User}s.
     *
     * @return List of {@link User}s which includes all database users or null if users not exists.
     */
    List<User> getAllUser();

    /**
     * Gets {@link User} from database that has the specified login.
     *
     * @param login This login is in the database. By this login the getting {@link User} from the database.
     * @return {@link User} who was getted by the given login or null, if the user with such login is not found.
     */
    User findUserByLogin(String login);


}
