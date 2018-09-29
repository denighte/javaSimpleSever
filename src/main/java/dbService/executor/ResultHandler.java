package dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dmitr on 9/29/2018.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
