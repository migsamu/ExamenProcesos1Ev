package org.iesfm.ioc.readers;

import org.iesfm.ioc.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class EmployeeReader {
    private final static Logger log = LoggerFactory.getLogger(EmployeeReader.class);

    private Scanner scanner;

    public EmployeeReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Employee read() {
        log.info("Introduce el nif");
        String nif = scanner.nextLine();
        log.info("Introduce el nombre");
        String name = scanner.nextLine();
        log.info("Introduce los apellidos");
        String surname = scanner.nextLine();

        return new Employee(nif, name, surname);
    }
}
