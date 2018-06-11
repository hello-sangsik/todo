package sangsik.todo.web.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sangsik.todo.domain.Post;
import sangsik.todo.service.PostService;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author sangsik.kim
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @GetMapping("/{id}")
    public PostDto read(@PathVariable final long id) {
        return PostDto.make(postService.get(id));
    }

    @GetMapping
    public List<PostDto> readAll(final Pageable pageable) {
        return postService.getAllByDeletedFalse(pageable)
                .stream()
                .map(PostDto::make)
                .collect(toList());
    }

    @PutMapping
    public PostDto update(@RequestBody final PostUpdateRequest postUpdateRequest) {
        return PostDto.make(postService.update(postUpdateRequest.toEntity()));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PostDto create(@RequestBody final PostCreateRequest postCreateRequest) {
        return PostDto.make(postService.save(postCreateRequest.toEntity()));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        postService.delete(id);
    }

    @Setter
    private static class PostCreateRequest {
        private String subject;
        private String content;

        private Post toEntity() {
            return Post.builder()
                    .subject(this.subject)
                    .content(this.content)
                    .build();
        }
    }

    @Setter
    private static class PostUpdateRequest {
        private long id;
        private String subject;
        private String content;

        private Post toEntity() {
            return Post.builder()
                    .id(this.id)
                    .subject(this.subject)
                    .content(this.content)
                    .build();
        }
    }

    @Getter
    private static class PostDto {
        private long id;
        private String subject;
        private String content;
        private String createdDate;
        private String modifiedDate;

        private PostDto(final Post post) {
            this.id = post.getId();
            this.subject = post.getSubject();
            this.content = post.getContent();
            this.createdDate = post.getFormattedCreateDate();
            this.modifiedDate = post.getFormattedModifiedDate();
        }

        static PostDto make(final Post post) {
            return new PostDto(post);
        }
    }
}

