package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.implementation.GroupRepositoryImplementation;
import org.junit.Test;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

    @Test
    public void add() {
        GroupDTO group = createTestGroup(1);
        _repository.add(group);
        GroupDTO queryResult = _repository.findById(group.getId());
        assertEquals(group.getName(), queryResult.getName());
    }

    @Test
    public void update() {
        GroupDTO group = createTestGroup(2);
        _repository.add(group);
        GroupDTO queryResult = _repository.findById(group.getId());
        queryResult.setDescription("newDescription");
        _repository.update(queryResult);
        queryResult = _repository.findById(group.getId());
        assertEquals("newDescription", queryResult.getDescription());
    }

    @Test
    public void addOrUpdate() {
        GroupDTO group = createTestGroup(3);
        _repository.addOrUpdate(group);
        GroupDTO queryResult = _repository.findById(group.getId());
        assertEquals(group.getName(), queryResult.getName());
    }

    @Test
    public void delete() {
        GroupDTO group = createTestGroup(4);
        _repository.add(group);
        _repository.delete(group);
        GroupDTO queryResult = _repository.findById(group.getId());
        assertNull(queryResult);
    }

    @Test
    public void findById() {
        GroupDTO group = createTestGroup(5);
        _repository.add(group);
        GroupDTO queryResult = _repository.findById(group.getId());
        assertEquals(group.getName(), queryResult.getName());
    }

    @Test
    public void findByName() {
        GroupDTO group = createTestGroup(6);
        _repository.add(group);
        List<GroupDTO> queryResult = _repository.findByName("%6");
        assertEquals(group.getName(), queryResult.get(0).getName());
    }

    @Override
    protected IGroupRepository Create() {
        return new GroupRepositoryImplementation();
    }
}