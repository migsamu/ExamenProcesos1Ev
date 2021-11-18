package org.iesfm.ioc;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Company {

    private String name;

    private List<Department> departments;

    public Company(String name, List<Department> departments) {
        this.name = name;
        this.departments = departments;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void deleteDepartment(String departmentName) {
        departments = departments
                .stream()
                .filter(department -> !department.getName().equals(departmentName))
                .collect(Collectors.toList());
    }

    public void addEmployeeToDepartment(String departmentName, Employee employee) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
                department.getEmployees().add(employee);
                break;
            }
        }
    }

    public void deleteEmployeeFromDepartment(String departmentName, String nif) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
                department.setEmployees(
                        department
                                .getEmployees()
                                .stream()
                                .filter(employee -> !employee.getNif().equals(nif))
                                .collect(Collectors.toList())
                );
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(departments, company.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, departments);
    }
}
