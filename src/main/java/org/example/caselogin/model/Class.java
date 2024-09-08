package org.example.caselogin.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classid")
    private Integer classId;

    @Column(name = "classname", nullable = false)
    private String className;

    @ManyToOne
    @JoinColumn(name = "teacherid")
    private User lecturer;

}
