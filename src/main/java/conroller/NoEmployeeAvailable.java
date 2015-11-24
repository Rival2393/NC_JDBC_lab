package conroller;

import java.sql.SQLException;

/**
 * Created by Dima on 22.11.2015.
 */
public class NoEmployeeAvailable extends SQLException {

    public NoEmployeeAvailable() {
        super();
    }

    public NoEmployeeAvailable(String reason) {
        super(reason);
    }
}
