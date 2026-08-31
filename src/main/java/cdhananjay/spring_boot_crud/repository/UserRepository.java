package cdhananjay.spring_boot_crud.repository;

import cdhananjay.spring_boot_crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByIdAndDeletedIsFalse(Long id);
    public List<User> findAllAndFindByDeletedIsFalse();
    public boolean existsByEmail(String email);
}
