package ru.tama.administrateuser.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.tama.administrateuser.entity.User;
import ru.tama.administrateuser.service.api.UserService;

import java.util.List;


/**
 * {@code UserManagementController} implements RESTful interface using {@link ResponseEntity}.
 * Using logger slf4j.
 *
 * @author tama
 */

@Controller
public class UserManagementController {
    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    /**
     * Retrieve all users from database using {@link UserService}
     *
     * @return {@link ResponseEntity} with (users, {@link HttpStatus}.OK) from database.
     */
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("getAllUser in controller start");

        List<User> userList = userService.getAllUser();

        logger.info("getAllUser in controller end - OK");
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    /**
     * Retrieve single user by id from Database using {@link UserService}
     *
     * @param id is id from response. He must match with id in database.
     * @return {@link ResponseEntity} with (user {@link HttpStatus}.OK) if users found or
     * {@link ResponseEntity} with ({@link HttpStatus}.NOT_FOUND) if users not found.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        logger.info("getUser in controller start - id:" + id);

        User user = userService.getUser(id);
        if (user == null) {
            logger.info("getUser in controller end - NOT_FOUND");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        logger.info("getUser in controller end - OK");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Creates user in database using {@link UserService} and sets location of the new User in HttpHeaders.
     *
     * @param user is a {@link User} that will be create. It enters from web.
     * @param uriComponentsBuilder
     * @return {@link ResponseEntity} with (headers,{@link HttpStatus}.OK) if user created. Headers - {@link HttpHeaders}
     * with new location or {@link ResponseEntity} with ({@link HttpStatus}.CONFLICT) if user not created.
     */
    @RequestMapping(value = "/user/", method = RequestMethod.POST )
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        logger.info("createUser in controller start - login: " + user.getLogin());

        if (!userService.createUserIfNotExists(user)) {
            logger.info("createUser in controller end - CONFLICT");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());

        logger.info("createUser in controller end - CREATED");
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Updates user in database using {@link UserService}.
     *
     * @param id is id from response. He must match with id in database.
     * @param user is {@link User} that must be updated. Enters from web.
     * @return {@link ResponseEntity} with (user, {@link HttpStatus}.OK) if user updated, or
     * {@link ResponseEntity} with {@link HttpStatus}.NOT_FOUND if user not updated.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        logger.info("updateUser in controller start - id: " + id);

        if (!userService.updateUserIfExists(user)) {
            logger.info("updateUser in controller end - NOT_FOUND");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        logger.info("updateUser in controller end - OK");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Deleted user from database using {@link UserService}.
     *
     * @param id is id from response. He must match with id in database.
     * @return {@link ResponseEntity} with ({@link HttpStatus}.NO_CONTENT) if user deleted, or
     * {@link ResponseEntity} with {@link HttpStatus}.NOT_FOUND if user not deleted.
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        logger.info("deleteUser in controller start");

        if (!userService.deleteUserIfExists(id)) {
            logger.info("deleteUser in controller end - NOT_FOUND");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        logger.info("deleteUser in controller end - NO_CONTENT");
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
