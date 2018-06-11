package sangsik.todo.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author sangsik.kim
 */
public class PostTest {


    @Test
    public void 삭제() {
        Post targetPost = Post.builder()
                .subject("제목")
                .content("내용")
                .build();

        targetPost.delete();

        assertThat(targetPost.isDeleted(), is(true));
    }

    @Test
    public void 수정() {
        Post targetPost = Post.builder()
                .subject("제목")
                .content("내용")
                .build();

        Post updatePost = Post.builder()
                .subject("수정된 제목")
                .content("내용12345")
                .build();

        targetPost.update(updatePost);

        assertThat(targetPost.getSubject(), is(updatePost.getSubject()));
        assertThat(targetPost.getContent(), is(updatePost.getContent()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 수정_제목_없음() {
        Post targetPost = Post.builder()
                .subject("제목")
                .content("내용")
                .build();

        Post updatePost = Post.builder()
                .subject("")
                .content("내용12345")
                .build();

        targetPost.update(updatePost);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 수정_내용_없음() {
        Post targetPost = Post.builder()
                .subject("제목")
                .content("내용")
                .build();

        Post updatePost = Post.builder()
                .subject("수정된 제목")
                .content("")
                .build();

        targetPost.update(updatePost);
    }
}