package com.employee.transactions.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@RequiredArgsConstructor
@Data
@Entity
@Table
@SQLDelete(sql = "update Employee set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean deleted;

    @OneToMany
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private List<Certificate> certificates;
}
