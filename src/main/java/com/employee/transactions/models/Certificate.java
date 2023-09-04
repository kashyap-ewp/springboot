package com.employee.transactions.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@RequiredArgsConstructor
@Data
@Entity
@Table
@SQLDelete(sql = "update Certificate set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Certificate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean deleted;
}
