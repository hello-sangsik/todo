package sangsik.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangsik.todo.domain.Post;
import sangsik.todo.repository.PostRepository;

import javax.persistence.EntityNotFoundException;

/**
 * @author sangsik.kim
 */
@Service
@AllArgsConstructor
public class PostService {
    private static final String POST_NOT_FOUND_MESSAGE = "게시물이 존재하지 않습니다";
    private PostRepository postRepository;

    public Page<Post> getAllByDeletedFalse(final Pageable pageable) {
        return postRepository.findByDeletedFalse(pageable);
    }

    public Post get(final long id) {
        return getByIdAndDeletedFalse(id);
    }

    public Post save(final Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post update(final Post post) {
        Post targetPost = getByIdAndDeletedFalse(post.getId());
        return targetPost.update(post);
    }

    @Transactional
    public void delete(final long id) {
        getByIdAndDeletedFalse(id).delete();
    }

    private Post getByIdAndDeletedFalse(final long id) {
        return postRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(POST_NOT_FOUND_MESSAGE));
    }
}
