package com.App.QCM.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "level")
public class Level {

    private static final long serialVersionID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AllLevel allLevel;
}
