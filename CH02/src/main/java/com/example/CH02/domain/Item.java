package com.example.CH02.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Item {
    @NotBlank
    @Size(min = 2, max = 255,message = "Name is required, maximum is 255 characters")
    private String name;


    @Future
    private Date auctionEnd;


    private Set<Bid> bids = new HashSet<>();


    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }


    public void addBid(Bid bid) {
        if(bid==null) throw new NullPointerException("Can't add a null Bid");
        if(bid.getItem()!=null) throw new IllegalArgumentException("Bid is already assigned to an Item");
        bids.add(bid);
        bid.setItem(this);
    }
}
