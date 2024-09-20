package com.example.CH04.repo;

import com.example.CH04.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    Iterable<Item> findByMetricWeight(double weight);
}
