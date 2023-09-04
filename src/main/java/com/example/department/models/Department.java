package com.example.department.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    private Employee manager;
}
