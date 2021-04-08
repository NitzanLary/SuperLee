package Employees.Tests;

import Employees.BuisnessLayer.Employee;
import Employees.BuisnessLayer.ResponseT;
import Employees.BuisnessLayer.Role;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    Employee employee;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        employee = new Employee("Nitzan", "205952971", LocalDate.now());
    }

    @org.junit.jupiter.api.Test
    void haveRoleCheck() {
        assertTrue(employee.getRoles().getValue().isEmpty());
        employee.AddRole(new Role("HR manager"));
        assertTrue(employee.haveRoleCheck("HR manager"));
    }

    @org.junit.jupiter.api.Test
    void checkAuthorizedHrOrGenral() {
        assertTrue(!employee.checkAuthorizedHrOrGenral().getValue());
        employee.AddRole(new Role("HR Manager"));
        assertTrue(employee.checkAuthorizedHrOrGenral().getValue());
    }
}