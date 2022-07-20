package com.dh.dentalClinic.dentalClinicAPI.persistence.entities;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long id;
    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String locality;

    @Column
    private String province;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Patient> patients = new HashSet<>();
    public Address() {
    }

    public Address(String street, String number, String locality, String province) {
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.province = province;
    }
}