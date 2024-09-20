package com.example.CH04.repo;

import com.example.CH04.model.ItemBidSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBidSummaryRepo extends JpaRepository<ItemBidSummary, Long> {
}
