package org.iesfm.ioc;

import org.iesfm.ioc.readers.CompanyReader;
import org.iesfm.ioc.readers.EmployeeReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final static Logger log = LoggerFactory.getLogger(Menu.class);

    private static final String MENU = "Selecciona la opción deseada" +
            "\n 1-. Listar todos los departamentos" +
            "\n 2-. Añadir empleado a departamento" +
            "\n 3-. Eliminar empleado de un departamento" +
            "\n 0-. SALIR";

    private Scanner scanner;
    private String message;
    private CompanyReader companyReader;
    private EmployeeReader employeeReader;

    public Menu(Scanner scanner, String message, CompanyReader companyReader, EmployeeReader employeeReader) {
        this.scanner = scanner;
        this.message = message;
        this.companyReader = companyReader;
        this.employeeReader = employeeReader;
    }

    private void printDepartments(List<Department> departments) {
        for (Department department : departments) {
            log.info(department.toString());
        }
    }

    public void run() {

        boolean salir = false;
        Integer selection = null;

        log.info(message);
        Company company = companyReader.readCompany();
        while (!salir) {
            log.info(MENU);
            selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 0:
                    log.info("Gracias por usar nuestra aplicacion;");
                    salir = true;
                    break;
                case 1:
                    List<Department> departments = company.getDepartments();
                    printDepartments(departments);
                    break;
                case 2:
                    log.info("Introduce el nombre del departamento");
                    String departmentName = scanner.nextLine();
                    Employee employee = employeeReader.read();
                    company.addEmployeeToDepartment(departmentName, employee);
                    break;
                case 3:
                    log.info("Introduce el nombre del departamento");
                    String departmentNameForDelete = scanner.nextLine();
                    log.info("Introduce el el nif");
                    String nif = scanner.nextLine();
                    company.deleteEmployeeFromDepartment(departmentNameForDelete, nif);
                    log.info("Empleado eliminado");
                    break;
                default:
                    log.error("Debe seleccionar una de las opciones");
            }


        }

    }
}
