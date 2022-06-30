package br.com.daniel.security.repository.statements;

public interface UserRoleRepositoryStatements {
    String SELECT_BY_USER_ID = "SELECT * FROM users_principal WHERE user_id=?";
}
