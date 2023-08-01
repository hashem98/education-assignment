package com.example.Education.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "mst_courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @Builder(toBuilder = true)
    public CourseEntity(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
