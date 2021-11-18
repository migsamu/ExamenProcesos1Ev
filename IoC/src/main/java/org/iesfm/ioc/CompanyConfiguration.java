package org.iesfm.ioc;

import org.iesfm.ioc.readers.CompanyReader;
import org.iesfm.ioc.readers.DepartmentReader;
import org.iesfm.ioc.readers.EmployeeReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
@PropertySource("application.properties")
public class CompanyConfiguration {

    @Bean
    public Menu menu(
            Scanner scanner,
            @Value("${company.message}") String message,
            CompanyReader companyReader,
            EmployeeReader employeeReader
    ) {
        return new Menu(scanner, message, companyReader, employeeReader);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public CompanyReader companyReader(Scanner scanner, DepartmentReader departmentReader) {
        return new CompanyReader(scanner, departmentReader);
    }

    @Bean
    public DepartmentReader departmentReader(Scanner scanner, EmployeeReader employeeReader) {
        return new DepartmentReader(scanner, employeeReader);
    }

    @Bean
    public EmployeeReader employeeReader(Scanner scanner) {
        return new EmployeeReader(scanner);
    }


}
