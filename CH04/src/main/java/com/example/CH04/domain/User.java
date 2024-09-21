package com.example.CH04.domain;

import com.example.CH04.Util.ZipcodeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name="USERS")
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private LocalDate registrationDate;
    private String email;
    private int level;
    private boolean active;
    @Convert(converter = ZipcodeConverter.class,
    attributeName = "city.zipCode")
    private Address homeAddress;
    @Embedded
    @AttributeOverride(name="street", column = @Column(name="BILLING_STREET"))
    @AttributeOverride(name="city.zipCode", column = @Column(name="BILLING_ZIPCODE"))
    @AttributeOverride(name="city.name", column = @Column(name="BILLING_CITY"))
    @AttributeOverride(name="city.country", column = @Column(name="BILLING_COUNTRY"))
    private Address billingAddress;

    @OneToMany(mappedBy="user")
    private Set<BillingDetails> billingDetails = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AddressEntity shippingAddress;
}
