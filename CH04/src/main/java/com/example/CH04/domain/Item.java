package com.example.CH04.domain;

import com.example.CH04.Util.MonetaryAmountConverter;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OrderBy;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private Set<Bid> bids = new HashSet<>();

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    //@Lob
    //private byte[] image;

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

    //private Set<Image> images = new HashSet<>();
    //private SortedSet<Image> images = new TreeSet<>();
    @ElementCollection
    @CollectionTable(
            name="IMAGE",
            joinColumns = @JoinColumn(name="ITEM_ID")
    )
    @Column(name="FILENAME")
    private Set<String> images = new HashSet<>();
    // the image table has two columns = item_id, filename



    @ElementCollection
    @CollectionTable(name="IMAGE")
    @MapKeyColumn(name="FILENAME")
    @Column(name="IMAGENAME")
    private Map<String,String> imagesMap = new HashMap<>();
    // the image table has 3 columns = item_id, filename,imagename
    //if the key was enum -> @MapKeyEnumerated
    // if the key was date -> @MapKeyTemporal


    @ElementCollection
    @CollectionTable(name="IMAGE")
    @MapKeyColumn(name="FILENAME")
    @Column(name="IMAGENAME")
    @SortComparator(ReverseStringComparator.class)
    private SortedMap<String,String> sortedImagesMap = new TreeMap<>();
    // keys are sorted in reverse order


    @ElementCollection
    @CollectionTable(name="IMAGE")
    @Column(name="FILENAME")
    @OrderBy("FILENAME asc")
    private Set<String> sortedImages = new LinkedHashSet<>();

    private void addImage(String image){
        images.add(image);
    }

    @ElementCollection
    @CollectionTable(
            name="IMAGE"
    )
    @AttributeOverride(name="filename", column = @Column(name="FNAME", nullable = false))
    private Set<Image> images1 = new HashSet<>();


    @ElementCollection
    @CollectionTable(name="IMAGE")
    @OrderBy("fileName ASC, width DESC")
    private Set<Image> sortedImages1 = new LinkedHashSet<>();


    @ElementCollection
    @CollectionTable(name="IMAGE")
    @MapKeyColumn(name="TITLE")
    private Map<String,Image> imagesMap1 = new HashMap<>();
    // now the filename can be null, since it's not a key


    @ElementCollection
    @CollectionTable(name="IMAGE")
    private Map<Filename,Image> imagesMap2 = new HashMap<>();
    //the @MapKeyColumn and @AttributeOverride have no effect when
    // the map's key is embeddable




}
