package br.com.daniel.security.dao;

import br.com.daniel.security.dao.statements.RoleDAOStatements;
import br.com.daniel.security.domain.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class RoleDAO {
    private final JdbcTemplate jdbcTemplate;

    public RoleDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Set<Role> findRolesByIdIn(final Set<String> ids) {
        final String inclusionPattern = ids.stream().map(id -> "?").collect(Collectors.joining(","));
        final String sql = String.format(RoleDAOStatements.SELECT_BY_ID, inclusionPattern);

        final List<Role> results = this.jdbcTemplate.query(
                sql,
                ps -> {
                    final List<String> listIds = new ArrayList<>(ids);
                    for (int i = 0; i < listIds.size(); i++) {
                        ps.setString(i + 1, listIds.get(i));
                    }
                },
                (rs, rowNum) -> new Role(
                        rs.getString("id"),
                        rs.getDate("created_at"),
                        rs.getString("created_by"),
                        rs.getDate("updated_at"),
                        rs.getString("updated_by"),
                        rs.getString("name")
                )
        );

        return new HashSet<>(results);
    }

    public Set<Role> findAllRoles() {
        final List<Role> results = this.jdbcTemplate.query(
                RoleDAOStatements.SELECT_ALL,
                (rs, rowNum) -> new Role(
                        rs.getString("id"),
                        rs.getDate("created_at"),
                        rs.getString("created_by"),
                        rs.getDate("updated_at"),
                        rs.getString("updated_by"),
                        rs.getString("name")
                )
        );

        return new HashSet<>(results);
    }

    public Role getAdminRole() {
        final List<Role> results = this.jdbcTemplate.query(
                RoleDAOStatements.SELECT_ADMIN_ROLE,
                (rs, rowNum) -> new Role(
                        rs.getString("id"),
                        rs.getDate("created_at"),
                        rs.getString("created_by"),
                        rs.getDate("updated_at"),
                        rs.getString("updated_by"),
                        rs.getString("name")
                )
        );

        return results.get(0);
    }
}
