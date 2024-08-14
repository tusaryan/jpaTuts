package com.tusaryan.jpaTutorial.jpaTuts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Data -> to make a POJO(plain old java object)
 * @AllArgsConstructor, @NoArgsConstructor -> to create constructor
 * @Builder -> to use the builder pattern
 * @Entity -> to make a pojo class as entity, it should always have a primary id/key. it tells hibernate to creates the corresponding data table inside our database.
 * @GeneratedValue -> to auto generate id. hibernate cretae the sql code to autogenetrate this.
 * ddl -> data definition layer, it contains all the command to manipulate our schema like create, alter, delete, drop the table etc.
 * spring.jpa.hibernate.ddl-auto=update/create/validate/create-drop/none
 * validate-> to validate from the entity that we have now and the entity schema that we are getting from our code, id they changed or not.to check for any change in java object in comparison with our DB
 * create-drop: create the DB asa(as soon as) our server runs and delete/drop that table as the server stops.
 * none -> when we don't want to change anything when our entites update
 * validate/none -> in production
 * update/create/create-drop-> in development phase
 *Assume JPA similar to interfaces in java with set of rule and specifications.
 * spring.jpa.show-sql=true -> to show all the queries hibernate is creating on our behalf
 * spring.jpa.properties.hibernate.format_sql=true -> to format all the query in readable form.
 * spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect  (Optional-> since spring data jpa can autodetect the dialect corresponding to the database connection provided in your classpath). Dialect -> it is something that defines the rule the hibernate use to convert jpql to the corresponding query language.
 * */

/**
 * Camel case converted to kebab case while storing in DB eg: updatedAt-> updated_at
 * @Column -> used to store different names rather than the java object name in DB
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(catalog = "product_catalog"), to group some of the tables in particular catalog, useful when we want to define multiple schema
@Table(
        name = "product_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "title_price_unique", columnNames = {"title_x", "price"})
        },
        indexes = {
                @Index(name = "sku_index", columnList = "sku") //for multiple columnList = "sku", ""
        }
)  //name field generally changes the name of database field different from that of java object/variable/class
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20) //so that this field is never null, length not more than 20.
    private String sku;

    @Column(name = "title_x")
    private String title;

    private BigDecimal price;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
