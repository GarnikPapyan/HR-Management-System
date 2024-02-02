package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "manager")
public class Manager extends  Employee{

    @Enumerated
    @Column(name = "managementLevel")
    private ManagementLevel managementLevel;
    @OneToOne
    @JoinColumn(name = "managedDepartment")
    private Department managedDepartment;


    public Manager() {
    }

    public Department getManagedDepartment() {
        return managedDepartment;
    }

    public void setManagedDepartment(Department managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public ManagementLevel getManagementLevel() {
        return managementLevel;
    }

    public void setManagementLevel(ManagementLevel managementLevel) {
        this.managementLevel = managementLevel;
    }
}
