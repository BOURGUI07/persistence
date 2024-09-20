package com.example.CH04.domain;

import com.example.CH04.Util.MonetaryAmountConverter;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @Column(nullable = false,name="ITEM_NAME")
    @Access(AccessType.PROPERTY)
    private String name;


    @Column(name= "START_PRICE",insertable = false)
    @ColumnDefault("1.00")
    @Generated(GenerationTime.INSERT)
    BigDecimal initialPrice;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;

    @Formula("""
            (SELECT AVG(B.AMOUNT) FROM BID B WHERE B.ITEM_ID = ID)
            """)
    private BigDecimal averageBidAmount;

    @Column(name="IMPERIALWEIGHT")
    @ColumnTransformer(
            read = "IMPERIALWEIGHT / 2.20462", // the Database convert lbs to kg when it fetches the weight
            write = "? * 2.20462" //The Database stores the weight in lbs
    )
    private Double metricWeight;


    @Enumerated(EnumType.STRING)
    @Column(name="AUCTION_TYPE",nullable = false)
    private AuctionType auctionType;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    Set<Bid> bids = new HashSet<>();

    @Lob
    private byte[] image;

    @Lob
    private String description;

    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name="PRICE",length = 63,nullable = false)
    private MonetaryAmount buyNowPrice;

    private Dimensions dimensions;
    private Weight weight;


    public void setName(String name){
        this.name = name.startsWith("AUCTION: ")? name: "AUCTION: " + name;
    }
}
