package com.case4.repo;

import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderRepo extends JpaRepository<Order,Long> {
    Iterable<Order> getAllByUser(User user);

    void deleteAllByUser(User user);

    @Query(
            value = "delete from orders where product_id = :product.id  and user_id = :user.id"
    )
    void removeByProductAndUser(Long id_product, Long id_user);
}
