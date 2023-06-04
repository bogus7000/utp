package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import org.junit.After;
import org.junit.Before;

import eu.glowacki.utp.assignment10.dtos.DTOBase;
import eu.glowacki.utp.assignment10.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {

    protected TRepository _repository;

    @Before
    public void before() throws SQLException {
        _repository = Create();
        if (_repository != null) {
            Connection conn = _repository.getConnection();
            _repository.beginTransaction(conn);
        }
    }

    //	I couldn't get rollbacks from @After working, perhaps due to
    //	Postgres-specific way of doing things that I am missing here?
    @After
    public void after() throws SQLException {
//        if (_repository != null) {
//            Connection conn = _repository.getConnection();
//            _repository.rollbackTransaction(conn);
//        }
    }

    protected abstract TRepository Create() throws SQLException;

    protected UserDTO createTestUser(int index) {
        UserDTO testUser = new UserDTO();
        testUser.setLogin("test_user" + index);
        testUser.setPassword("password" + index);
        return testUser;
    }

    protected GroupDTO createTestGroup(int index) {
        GroupDTO testGroup = new GroupDTO();
        testGroup.setName("test_group" + index);
        testGroup.setDescription("test_group" + index);
        return testGroup;
    }
}