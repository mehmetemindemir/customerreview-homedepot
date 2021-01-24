package ca.homedepot.customerreview.dao;

import ca.homedepot.customerreview.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserModel, Long> {
}
