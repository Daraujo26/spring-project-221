package edu.psu.petstorespring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Double weight;
    private Integer age;

    private String imageUrl;

    // Constructors

    public Pet(Long id, String name, String type, Double weight, Integer age, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public Pet(Long id, String name, String type, Double weight, Integer age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.age = age;
    }
    public Pet() {

    }

    // Getters
    public Long getId() { return this.id; }

    public String getName() { return this.name; }

    public String getType() { return this.type; }

    public Double getWeight() { return this.weight; }

    public Integer getAge() { return this.age; }

    public String getImageUrl() { return this.imageUrl; }

    // Setters
    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setType(String type) { this.type = type; }

    public void setWeight(Double weight) { this.weight = weight; }

    public void setAge(Integer age) { this.age = age; }

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
}
