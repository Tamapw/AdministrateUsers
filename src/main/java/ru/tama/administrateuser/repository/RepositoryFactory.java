package ru.tama.administrateuser.repository;

import ru.tama.administrateuser.repository.api.UserRepository;
import ru.tama.administrateuser.repository.impl.UserReposirotyImpl;

/**
 * This class is factory of repository.
 *
 * @author tama
 */
public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory = new RepositoryFactory();

    public static RepositoryFactory getRepositoryFactory() {
        return repositoryFactory;
    }

    public UserRepository getUserRepository() {
        return new UserReposirotyImpl();
    }
}
