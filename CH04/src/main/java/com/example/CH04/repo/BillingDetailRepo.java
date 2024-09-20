package com.example.CH04.repo;

import com.example.CH04.domain.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BillingDetailRepo<T extends BillingDetails, ID> extends JpaRepository<T, ID>{
    List<T> findByOwner(String owner);
}
