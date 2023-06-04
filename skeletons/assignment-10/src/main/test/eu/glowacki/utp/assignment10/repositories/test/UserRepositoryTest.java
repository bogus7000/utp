package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.implementation.UserRepositoryImpl;
import org.junit.Test;

import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

    @Test
    public void add() {
        UserDTO user = createTestUser(1);
        _repository.add(user);
        UserDTO queryResult = _repository.findById(user.getId());
        assertEquals(user.getLogin(), queryResult.getLogin());
    }

    @Test
    public void update() {
        UserDTO user = createTestUser(2);
        _repository.add(user);
        UserDTO queryResult = _repository.findById(user.getId());
        queryResult.setPassword("newPassword");
        _repository.update(queryResult);
        queryResult = _repository.findById(user.getId());
        assertEquals("newPassword", queryResult.getPassword());
    }

    @Test
    public void addOrUpdate() {
        UserDTO user1 = createTestUser(3);
        _repository.addOrUpdate(user1);
        UserDTO queryResult = _repository.findById(user1.getId());
        assertEquals(user1.getLogin(), queryResult.getLogin());
    }

    @Test
    public void delete() {
        UserDTO user = createTestUser(4);
        _repository.add(user);
        _repository.delete(user);
        UserDTO queryResult = _repository.findById(user.getId());
        assertNull(queryResult);
    }

    @Test
    public void findById() {
        UserDTO user = createTestUser(5);
        _repository.add(user);
        UserDTO queryResult = _repository.findById(user.getId());
        assertEquals(user.getLogin(), queryResult.getLogin());
    }

    @Test
    public void findByName() {
        UserDTO user = createTestUser(6);
        _repository.add(user);
        List<UserDTO> queryResult = _repository.findByName("%6");
        assertEquals(user.getLogin(), queryResult.get(0).getLogin());
    }

    @Override
    protected IUserRepository Create() throws SQLException {
        return new UserRepositoryImpl();
    }
}