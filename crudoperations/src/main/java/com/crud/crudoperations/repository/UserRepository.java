package com.crud.crudoperations.repository;
import com.crud.crudoperations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByNameContainingIgnoreCase(String name);

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByMobile(String mobile);

    // Custom multiple-field query (optional, for combining filters manually)
    List<User> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email);

    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);
    Page<User> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email, Pageable pageable);
}
