package com.example.shoppingmall.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepositoryLegacy {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DataSource dataSource;

    public void makeConnection() {
        DataSourceUtils.getConnection(dataSource);
    }

    public void save(User user) {
        this.entityManager.persist(user);
    }

    public User findByLoginInfo(Map<String, String> loginInfo) {
        String userId = loginInfo.get("user_id");
        String pw = loginInfo.get("pw");

        String jpql = "SELECT u FROM User u WHERE u.userId = :userId AND u.pw = :pw";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);

        List<User> loginUser = query
            .setParameter("userId", userId)
            .setParameter("pw", pw)
            .getResultList();

        return loginUser.isEmpty() ? null : loginUser.get(0);
    }

    public User findByUserId(String userId) {
        String jpql = "SELECT u FROM User u WHERE u.userId = :userId";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);

        List<User> users = query
            .setParameter("userId", userId)
            .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    public User findById(int id) {
        return this.entityManager.find(User.class, id);
    }
}
