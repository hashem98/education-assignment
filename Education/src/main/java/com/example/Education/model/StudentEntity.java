package com.example.Education.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "mst_students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "english_full_name")
    private String englishFullName;

    @Column(name = "arabic_full_name")
    private String arabicFullName;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "address")
    private String address;

    @Builder(toBuilder = true)
    public StudentEntity(Long id, String englishFullName, String arabicFullName, String email, String telephoneNumber, String address) {
        this.id = id;
        this.englishFullName = englishFullName;
        this.arabicFullName = arabicFullName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }
}
