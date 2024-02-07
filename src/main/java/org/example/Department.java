package org.example;

import jakarta.persistence.*;

import java.util.Set;
@Cacheable
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(name = "departmentName")
    private String departmentName;
    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    @OneToOne(mappedBy = "managedDepartment",cascade = CascadeType.REMOVE)
    private Manager departmentHead;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Manager getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Manager departmentHead) {
        this.departmentHead = departmentHead;
    }

    public Department() {
    }


    public Set<Employee> getEmployeeSet() {
        return employees;
    }

    public void setEmployeeSet(Set<Employee> employees) {
        this.employees = employees;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
