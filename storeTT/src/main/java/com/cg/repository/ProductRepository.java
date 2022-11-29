package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


//    List<ProductDTO> getAllProduct();

    @Modifying
    @Query("UPDATE Product AS p SET p.deleted = true WHERE p.id = :productId")
    void softDelete(@Param("productId") Long productId);

    @Query("SELECT NEW com.cg.model.dto.ProductDTO(" +
            "p.id, " +
            "p.name, " +
            "p.amount, " +
            "p.price, " +
            "p.description, " +
            "p.avatar "+
            ") " +
             " FROM Product p " +
           "WHERE p.deleted = false "
                   )
    List<ProductDTO> getAllProductWhereDeletedIsFalse();
}
