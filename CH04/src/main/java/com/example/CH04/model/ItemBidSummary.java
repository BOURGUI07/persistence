package com.example.CH04.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Immutable
@Subselect(value= """
        select i.ID as ITEMID, i.NAME as NAME, count(b.ID) as NUMBEROFBIDS
        from ITEM i left join BID b on i.ID = b.ITEM_ID 
        group by i.ID, i.NAME
""")
@Synchronize({"BID","ITEM"})
@Getter
public class ItemBidSummary {
    @Id
    private Long itemId;
    private String name;
    private long numberOfBids;
}
