package eu.glowacki.utp.assignment10.implementation;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements IUserRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void add(UserDTO dto) {
        Connection conn = null;
        String sql = "INSERT INTO utp10.users (user_login, user_password) VALUES (?, ?)";

        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            beginTransaction(conn);

            stmt.setString(1, dto.getLogin());
            stmt.setString(2, dto.getPassword());
            int affectedRows = stmt.executeUpdate();
            System.out.println(affectedRows);
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    dto.setId(userId);
                    if (dto.getGroups() != null) {
                        addUserGroups(conn, userId, dto.getGroups());
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
    public void update(UserDTO dto) {
        Connection conn = null;
        String sql = "UPDATE utp10.users SET user_login = ?, user_password = ? WHERE user_id = ?";

        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            beginTransaction(conn);

            stmt.setString(1, dto.getLogin());
            stmt.setString(2, dto.getPassword());
            stmt.setInt(3, dto.getId());
            stmt.executeUpdate();
            updateUserGroups(conn, dto.getId(), dto.getGroups());

            commitTransaction(conn);
        } catch (SQLException e) {
            if (conn != null) {
                rollbackTransaction(conn);
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addOrUpdate(UserDTO dto) {
        if (this.exists(dto)) {
            this.update(dto);
        } else {
            this.add(dto);
        }
    }

    @Override
    public void delete(UserDTO dto) {
        Connection conn = null;
        String sql = "DELETE FROM utp10.users WHERE user_id = ?";

        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            beginTransaction(conn);

            stmt.setInt(1, dto.getId());
            stmt.executeUpdate();
            deleteUserGroups(conn, dto.getId());

            commitTransaction(conn);
        } catch (SQLException e) {
            if (conn != null) {
                rollbackTransaction(conn);
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserDTO findById(int id) {
        String sql = "SELECT * FROM utp10.users WHERE user_id = ?";
        UserDTO user = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserDTO();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("user_login"));
                user.setPassword(rs.getString("user_password"));
                user.setGroups(getUserGroups(conn, user.getId()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public List<UserDTO> findByName(String username) {
        String sql = "SELECT * FROM utp10.users WHERE user_login LIKE ?";
        List<UserDTO> users = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("user_login"));
                user.setPassword(rs.getString("user_password"));
                user.setGroups(getUserGroups(conn, user.getId()));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
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
        String sql = "SELECT * FROM utp10.users";

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
    public boolean exists(UserDTO dto) {
        String sql = "SELECT * FROM utp10.users WHERE user_id = ?";

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


    private List<GroupDTO> getUserGroups(Connection conn, int userId) {
        List<GroupDTO> groups = new ArrayList<>();
        String sql = "SELECT g.* FROM utp10.groups g INNER JOIN utp10.users_groups ug ON g.group_id = ug.group_id WHERE ug.user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GroupDTO group = new GroupDTO();
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));
                group.setDescription(rs.getString("group_description"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }


    private void addUserGroups(Connection conn, int userId, List<GroupDTO> groups) throws SQLException {
        String sql = "INSERT INTO utp10.users_groups (user_id, group_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (GroupDTO group : groups) {
                stmt.setInt(1, userId);
                stmt.setInt(2, group.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void updateUserGroups(Connection conn, int userId, List<GroupDTO> groups) throws SQLException {
        deleteUserGroups(conn, userId);
        addUserGroups(conn, userId, groups);
    }

    private void deleteUserGroups(Connection conn, int userId) throws SQLException {
        String sql = "DELETE FROM utp10.users_groups WHERE user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}
