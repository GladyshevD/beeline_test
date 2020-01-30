package ru.beeline.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "stores", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"city", "address"},
                name = "stores_unique_city_address_idx")})
@Data
public class SalesPointDo {
    public static final int START_SEQ = 1000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @JsonIgnore
    private int id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String name;

    @Column(name = "city", nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String city;

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String address;

    @Column(name = "registered", nullable = false,
            columnDefinition = "timestamp default now()")
    @NotNull
    @JsonIgnore
    private Date registered = new Date();

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    @JsonIgnore
    private boolean enabled = true;

    public SalesPointDo() {
    }

    public SalesPointDo(int id, String name, String city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
