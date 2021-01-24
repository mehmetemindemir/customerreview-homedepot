package ca.homedepot.customerreview.dao;

import ca.homedepot.customerreview.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<ProductModel, Long> {
}
