package dbService.dao;

import account.UserProfile;
import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UserProfile get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UserProfile(result.getString(2), result.getString(3));
        });
    }

    public UserProfile get(String login) throws SQLException {
        return executor.execQuery("select * from users where login='" + login + "'", result -> {
            result.next();
            return new UserProfile(result.getString(2), result.getString(3));
        });
    }

    public long getUserId(UserProfile user) throws SQLException {
        return executor.execQuery("select * from users where login='" + user.getLogin() + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(UserProfile name) throws SQLException {
        executor.execUpdate("insert into users (login, password) values ('" + name.getLogin() + "', '" + name.getPass() + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
