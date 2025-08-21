
package com.employee.dao;

import java.sql.*;
import java.util.*;
import com.employee.model.Employee;

public class EmployeeDAO {

    // DB connection info
    private String jdbcURL = "jdbc:mysql://localhost:3306/testdb";
    private String jdbcUser = "root";
    private String jdbcPassword = "rootroot";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    rs.getString("department")
                );
                employees.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees; // Never null
    }

    // Insert
    public void insertEmployee(Employee emp) {
    	//Prevents SQL INjection - Prepared Statement
        String sql = "INSERT INTO employee(name, salary, department) VALUES (?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setString(3, emp.getDepartment());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get one employee by id (for editing form)
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("department")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Not found
    }

    // Update
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name=?, salary=?, department=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setString(3, emp.getDepartment());
            ps.setInt(4, emp.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*package com.employee.dao;

import java.sql.*;
import java.util.*;
import com.employee.model.Employee;

public class EmployeeDAO {

    // DB connection info
    private String jdbcURL = "jdbc:mysql://localhost:3306/testdb";
    private String jdbcUser = "root";
    private String jdbcPassword = "rootroot";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    rs.getString("department")
                );
                employees.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees; //  Never null
    }


    public void insertEmployee(Employee emp) {
        String sql = "INSERT INTO employee(name, salary, department) VALUES (?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setString(3, emp.getDepartment());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
