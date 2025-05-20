package ssafy.project07.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 user 찾기
    Optional<User> findByEmail(String email);
}