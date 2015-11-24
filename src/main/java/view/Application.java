package view;

import conroller.DataAccessObject;
import model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class Application {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static DataAccessObject dao;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "lab_8";
    private static final String PASS = "root";
    private static Connection connection;


    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver OK");
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database available");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        catch (ClassNotFoundException e){}
        dao = new DataAccessObject(connection);

        boolean exit = false;

        int choice;

        while(!exit){
            System.out.println("Choose operation to proceed");
            System.out.println("Enter 1 to get employee by id");
            System.out.println("Enter 2 to add new employee");
            System.out.println("Enter 3 to remove employee");
            System.out.println("Enter 0 to exit");
            try {
                choice = Integer.parseInt(reader.readLine());
                System.in.skip(System.in.available());
                switch (choice){
                    case 1:
                        getEmployee(dao);
                        break;
                    case 2:
                        addEmployee(dao);
                        break;
                    case 3:
                        removeEmployee(dao);
                        break;
                    case 0:
                        exit = true;
                        break;
                }
            }
            catch (IOException e){
                return;
            }
        }
    }

    private static void getEmployee(DataAccessObject dao) throws IOException {
        try {
            Employee employee;
            System.out.println("Please enter the ID: ");
            int id = Integer.parseInt(reader.readLine());
            employee = dao.selectEmployee(id);
            System.out.println(employee.toString());
        }
        catch (SQLException e){
            System.err.println("Wrong ID");
            Runtime.getRuntime().exec("cls");
            //getEmployee(dao);
        }
        catch (IOException e){
            System.err.println("Wrong input");
        }
    }

    private static void removeEmployee(DataAccessObject dao) throws IOException {
        try{
            System.out.println("Please enter the ID: ");
            int id = Integer.parseInt(reader.readLine());
            dao.removeEmployee(id);
            System.out.println("Employee removed\n");
        }
        catch (IOException e){
            System.err.println("Wrong input");
        }
        catch (SQLException e){
            System.err.println("Wrong ID");
            Runtime.getRuntime().exec("cls");
            //removeEmployee(dao);
        }
    }

    private static void addEmployee(DataAccessObject dao) throws IOException {
        try{
            Employee employee = new Employee();
            System.out.println("Please enter info: ");
            System.out.println("First name: ");
            employee.setFirstName(reader.readLine());
            System.out.println("Last name: ");
            employee.setLastName(reader.readLine());
            System.out.println("Email: ");
            employee.setEmail(reader.readLine());
            dao.addEmployee(employee);
            System.out.println("Employee inserted\n");
        }
        catch (IOException e){

        }
        catch (SQLException e){
            System.err.println("Can't write these data");
        }
    }
}
