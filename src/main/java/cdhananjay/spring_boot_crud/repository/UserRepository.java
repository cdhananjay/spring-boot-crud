package cdhananjay.spring_boot_crud.repository;

import cdhananjay.spring_boot_crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
