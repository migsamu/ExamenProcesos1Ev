package org.iesfm.ioc.readers;

import org.iesfm.ioc.Company;
import org.iesfm.ioc.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CompanyReader {

    private final static Logger log = LoggerFactory.getLogger(CompanyReader.class);

    private Scanner scanner;
    private DepartmentReader departmentReader;

    public CompanyReader(Scanner scanner, DepartmentReader departmentReader) {
        this.scanner = scanner;
        this.departmentReader = departmentReader;
    }

    public Company readCompany() {

        log.info("Introduce el nombre de la empresa");
        String name = scanner.nextLine();
        log.info("Indica el número de departamentos que tiene la empresa");
        int departmentSize = scanner.nextInt();
        scanner.nextLine();

        List<Department> departments = new LinkedList<>();
        for (int i = 0; i < departmentSize; i++) {
            departments.add(departmentReader.read());
        }
        return new Company(name, departments);
    }
}
