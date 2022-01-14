package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.user.User;



public interface ProductRepository extends CrudRepository<Product, Integer>{
    List<Product> findAll();
    
    @Query("SELECT ptype FROM ProductType ptype WHERE ptype.name =:name")
    ProductType getProductType(@Param("name")String name);
    
    @Query("SELECT type FROM ProductType type")
    List<ProductType> findAllProductTypes();
    
    @Query("SELECT product FROM Product product WHERE product.price<:price")
    List<Product> findByPriceLessThan(@Param("price") Double price);
    
    Optional<Product> findById(int id);
    Product findByName(String name);
    Product save(Product p);
}
