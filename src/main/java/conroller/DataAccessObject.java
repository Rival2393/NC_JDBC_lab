package conroller;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dima on 22.11.2015.
 */
public class DataAccessObject {
    private Connection connection;

    public DataAccessObject(Connection connection) {
        this.connection = connection;
    }

    private static final String SELECT_EMPLOYEE_BY_ID = "select e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, j.job_title, d.department_name, l.city\n" +
            "from employees e join departments d on e.department_id = d.department_id\n" +
            "join locations l on d.location_id = l.location_id \n" +
            "join jobs j on e.job_id = j.job_id \n" +
            "where employee_id = ?";

    private static final String REMOVE_EMPLOYEE_BY_ID = "delete from employees where employee_id = ?";

    private static final String ADD_EMPLOYEE = "insert into employees (employee_id, first_name," +
            "last_name, email, hire_date, job_id) values" +
            "(?, ?, ?, ?,sysdate, 'IT_PROG')";

    private static final String GET_AVAILABLE_ID = "select (max(employee_id)+1) from employees";

    private int getAvailableId() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_AVAILABLE_ID);
        ResultSet set = statement.executeQuery();
        if(set.next()) return set.getInt(1);
        throw new SQLException();
    }

    public Employee selectEmployee(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();
        if(set.next()){
            Employee employee = new Employee(id);
            employee.setFirstName(set.getString("FIRST_NAME"));
            employee.setLastName(set.getString("LAST_NAME"));
            employee.setEmail(set.getString("EMAIL"));
            employee.setPhoneNumber(set.getString("PHONE_NUMBER"));
            employee.setHireDate(set.getDate("HIRE_DATE"));
            employee.setSalary(set.getDouble("SALARY"));
            employee.setJobName(set.getString("JOB_TITLE"));
            employee.setDepartmentName(set.getString("DEPARTMENT_NAME"));
            employee.setCity(set.getString("CITY"));
            return employee;
        }
        throw new NoEmployeeAvailable();
    }

    public void removeEmployee(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(REMOVE_EMPLOYEE_BY_ID);
        statement.setInt(1, id);
        int deletedEmps = statement.executeUpdate();
        if(deletedEmps == 0) throw new NoEmployeeAvailable();
    }

    public void addEmployee(Employee employee) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEE);
        int id = getAvailableId();
        statement.setInt(1, id);
        statement.setString(2, employee.getFirstName());
        statement.setString(3, employee.getLastName());
        statement.setString(4, employee.getEmail());
        statement.executeUpdate();
    }
}
