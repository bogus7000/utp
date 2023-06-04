package eu.glowacki.utp.assignment10.implementation;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryImplementation implements IGroupRepository {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres";

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        return conn;
    }

    @Override
    public void add(GroupDTO dto) {
        Connection conn = null;
        String sql = "INSERT INTO utp10.groups (group_name, group_description) VALUES (?, ?)";

        try {
            conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            beginTransaction(conn);

            stmt.setString(1, dto.getName());
            stmt.setString(2, dto.getDescription());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int groupId = generatedKeys.getInt(1);
                    dto.setId(groupId);
                    if (dto.getUsers() != null) {
                        addGroupUsers(conn, groupId, dto.getUsers());
                    }
                }
            }

            commitTransaction(conn);
        } catch (SQLException e) {
            if (conn != null) {
                rollbackTransaction(conn);
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(GroupDTO dto) {
        Connection conn = null;
        String sql = "UPDATE utp10.groups SET group_name = ?, group_description = ? WHERE group_id = ?";

        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            beginTransaction(conn);

            stmt.setString(1, dto.getName());
            stmt.setString(2, dto.getDescription());
            stmt.setInt(3, dto.getId());
            stmt.executeUpdate();
            updateGroupUsers(conn, dto.getId(), dto.getUsers());

            commitTransaction(conn);
        } catch (SQLException e) {
            if (conn != null) {
                rollbackTransaction(conn);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        if (this.exists(dto)) {
            this.update(dto);
        } else {
            this.add(dto);
        }
    }

    @Override
    public void delete(GroupDTO dto) {
        Connection conn = null;
        String sql = "DELETE FROM utp10.groups WHERE group_id = ?";

        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            beginTransaction(conn);

            stmt.setInt(1, dto.getId());
            stmt.executeUpdate();
            deleteGroupUsers(conn, dto.getId());

            commitTransaction(conn);
        } catch (SQLException e) {
            if (conn != null) {
                rollbackTransaction(conn);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public GroupDTO findById(int id) {
        String sql = "SELECT * FROM utp10.groups WHERE group_id = ?";
        GroupDTO group = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                group = new GroupDTO();
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));
                group.setDescription(rs.getString("group_description"));
                group.setUsers(getGroupUsers(conn, group.getId()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return group;
    }

    @Override
    public List<GroupDTO> findByName(String name) {
        List<GroupDTO> groups = new ArrayList<>();
        String sql = "SELECT * FROM utp10.groups WHERE group_name LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GroupDTO group = new GroupDTO();
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));
                group.setDescription(rs.getString("group_description"));
                group.setUsers(getGroupUsers(conn, group.getId()));
                groups.add(group);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public void beginTransaction(Connection conn) {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitTransaction(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollbackTransaction(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        String sql = "SELECT * FROM utp10.groups";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.last();
                count = rs.getRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public boolean exists(GroupDTO dto) {
        String sql = "SELECT * FROM utp10.groups WHERE group_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dto.getId());
            ResultSet rs = stmt.executeQuery();
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private List<UserDTO> getGroupUsers(Connection conn, int groupId) {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT u.* FROM utp10.users u INNER JOIN utp10.users_groups ug ON u.user_id = ug.user_id WHERE ug.group_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, groupId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("user_login"));
                user.setPassword(rs.getString("user_password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private void addGroupUsers(Connection conn, int groupId, List<UserDTO> users) throws SQLException {
        String sql = "INSERT INTO utp10.users_groups (user_id, group_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (UserDTO user : users) {
                stmt.setInt(1, user.getId());
                stmt.setInt(2, groupId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void updateGroupUsers(Connection conn, int groupId, List<UserDTO> users) throws SQLException {
        deleteGroupUsers(conn, groupId);
        addGroupUsers(conn, groupId, users);
    }

    private void deleteGroupUsers(Connection conn, int groupId) throws SQLException {
        String sql = "DELETE FROM utp10.users_groups WHERE group_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, groupId);
            stmt.executeUpdate();
        }
    }
}
