package de.fhws.fiw.fds.suttondemo.server.database.hibernate;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.UserDao;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.dao.UserDaoHibernate;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.dao.UserDaoHibernateImpl;

import java.util.stream.Collectors;

public class UserDaoAdapter implements UserDao {

    private final UserDaoHibernate userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public SingleModelResult<User> readUserByUsername(String username) {
        SingleModelHibernateResult<UserDB> userFromDB = userDaoHibernate.readUserByUsername(username);
        return createFrom(userFromDB);
    }

    @Override
    public NoContentResult create(User model) {
        return userDaoHibernate.create(createFrom(model));
    }

    @Override
    public SingleModelResult<User> readById(long id) {
        SingleModelHibernateResult<UserDB> userFromDB = userDaoHibernate.readById(id);
        return createFrom(userFromDB);
    }

    @Override
    public CollectionModelResult<User> readAll(SearchParameter searchParameter) {
        CollectionModelHibernateResult<UserDB> result = userDaoHibernate.readAll(searchParameter);
        return createFrom(result);
    }

    @Override
    public NoContentResult update(User model) {
        UserDB userToUpdate = createFrom(model);
        return userDaoHibernate.update(userToUpdate);
    }

    @Override
    public NoContentResult delete(long id) {
        return userDaoHibernate.delete(id);
    }

    private CollectionModelResult<User> createFrom(CollectionModelHibernateResult<UserDB> users) {
        return users.getResult().stream()
                .map(this::createFrom)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModelResult::new));
    }

    private User createFrom(UserDB user) {
        User result = new User(user.getName(), user.getPassword(), user.getRole());
        result.setId(user.getId());
        return result;
    }

    private SingleModelResult<User> createFrom(SingleModelHibernateResult<UserDB> userFromDB) {
        if (!userFromDB.isEmpty()) {
            UserDB userDB = userFromDB.getResult();
            User user = createFrom(userDB);
            return new SingleModelResult<>(user);
        }

        return new SingleModelResult<>();
    }

    private UserDB createFrom(User user) {
        UserDB result = new UserDB(user.getName(), user.getSecret(), user.getRole());
        result.setId(user.getId());
        return result;
    }
}
