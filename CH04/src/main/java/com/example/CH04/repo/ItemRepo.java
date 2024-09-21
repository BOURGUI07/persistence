package com.example.CH04.repo;

import com.example.CH04.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    Iterable<Item> findByMetricWeight(double weight);
    @Query(value = "SELECT FILENAME FROM IMAGE WHERE ITEM_ID= ?1",
    nativeQuery = true)
    Set<String> findImagesNative(Long id);
}
