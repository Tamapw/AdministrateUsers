package ru.tama.administrateuser.repository.api;

import ru.tama.administrateuser.entity.User;

import java.util.List;

/**
 * This interface implements work with an {@link User} entity, such as creation, deletion, updating,
 * finding and getting for an entity in the database.
 *
 * @author tama
 */
public interface UserRepository {
    /**
     * Create {@link User} in database.
     *
     * @param user the {@link User} that need to be created.
     * @return The {@link User} that was created(with the assigned id) or null if the creation failed.
     */
    User createUser(User user);

    /**
     * Delete {@link User} from database.
     *
     * @param user the {@link User} that need to be deleted.
     */
    void deleteUser(User user);

    /**
     * Update {@link User} in database.
     *
     * @param user the {@link User} that need to be updating.
     */
    void updateUser(User user);

    /**
     *  Gets ALL database users and return them.
     *
     * @return List of {@link User}s which includes all database users.
     */
    List<User> getAllUser();

    /**
     * Gets {@link User} from database that has the specified id.
     *
     * @param id This id is in the database. By this id the getting {@link User} from the database.
     * @return The {@link User} who was getted by the given id.
     */
    User getUserById(Integer id);

    /**
     * Gets list of {@link User} from database that has the specified login.
     *
     * @param login This login is in the database. By this login the getting {@link User} from the database.
     * @return The list of {@link User} who was getted by the given login.
     */
    List<User> findByLogin(String login);
}
