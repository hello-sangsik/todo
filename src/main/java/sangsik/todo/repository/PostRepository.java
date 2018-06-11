package sangsik.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sangsik.todo.domain.Post;

import java.util.Optional;

/**
 * @author sangsik.kim
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndDeletedFalse(final long id);

    Page<Post> findByDeletedFalse(final Pageable pageable);
}
