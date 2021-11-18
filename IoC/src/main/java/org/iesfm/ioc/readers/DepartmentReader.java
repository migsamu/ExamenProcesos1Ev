package org.iesfm.ioc.readers;

import org.iesfm.ioc.Department;
import org.iesfm.ioc.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DepartmentReader {

    private final static Logger log = LoggerFactory.getLogger(DepartmentReader.class);

    private Scanner scanner;
    private EmployeeReader employeeReader;

    public DepartmentReader(Scanner scanner, EmployeeReader employeeReader) {
        this.scanner = scanner;
        this.employeeReader = employeeReader;
    }

    public Department read() {
        log.info("Introduce el nombre del departamento: ");
        String name = scanner.nextLine();
        log.info("¿Cuántos empleados tiene el departamento?");
        int employeesSize = scanner.nextInt();
        scanner.nextLine();

        List<Employee> employees = new LinkedList<>();
        for (int i = 0; i < employeesSize; i++) {
            employees.add(employeeReader.read());
        }

        return new Department(name, employees);
    }
}
