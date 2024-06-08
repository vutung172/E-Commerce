package com.main.ra.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TaxCategoriesSetting", schema = "ecommerce")
public class TaxCategoriesEntity {
    @Id
    @Column(name = "tax_category_id",unique = true )
    @NotNull
    private Long taxCategoryId;

    @Column(name = "tax_category_name")
    private String taxCategoryName;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "round_rule")
    private String roundRule;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "responsible_object")
    private String resposibleObject;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "updated_user_id")
    private Long updatedUserId;
}
