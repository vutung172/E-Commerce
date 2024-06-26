package com.main.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories",schema = "ecommerce")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Size(max = 100,message = "{message.Max-Length-100}")
    @NotNull(message = "{message.NotNull}")
    @Column(name = "category_name", unique = true)
    private String categoryName;

    @Column(name = "description")
    private String description;

    /*@Generated(event = {EventType.INSERT,EventType.UPDATE})*/
    @Column(name = "status", insertable = false)
    private Boolean status;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();

}