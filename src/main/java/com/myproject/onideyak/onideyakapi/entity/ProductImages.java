package com.myproject.onideyak.onideyakapi.entity;

import com.myproject.onideyak.onideyakapi.entity.share.FileResource;
import lombok.*;

import javax.persistence.*;

@Entity(name = "product_image_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductImages {
    @Id
    @Column(name = "property_id", length = 255)
    private String propertyId;

    @Embedded
    private FileResource resource;

    @ManyToOne
    private Product product;

}
