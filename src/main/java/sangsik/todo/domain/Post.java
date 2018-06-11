package sangsik.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;

/**
 * @author sangsik.kim
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post extends BaseEntity {

    @Builder
    public Post(long id, String subject, String content) {
        super(id);
        this.subject = subject;
        this.content = content;
    }

    private String subject;

    private String content;

    private boolean deleted;

    public Post update(Post post) {
        validationArguments(post);
        this.subject = post.subject;
        this.content = post.content;
        return this;
    }

    private void validationArguments(Post post) {
        if (StringUtils.isEmpty(post.subject) || StringUtils.isEmpty(post.content)) {
            throw new IllegalArgumentException();
        }
    }

    public Post delete() {
        this.deleted = true;
        return this;
    }
}
