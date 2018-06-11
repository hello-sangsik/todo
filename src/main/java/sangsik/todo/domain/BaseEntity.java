package sangsik.todo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sangsik.kim
 */
@Getter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    private static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

    BaseEntity(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdDate;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @JsonIgnore
    public String getFormattedCreateDate() {
        return getFormattedDate(this.createdDate, DEFAULT_DATE_FORMAT);
    }

    @JsonIgnore
    public String getFormattedModifiedDate() {
        return getFormattedDate(this.modifiedDate, DEFAULT_DATE_FORMAT);
    }

    private String getFormattedDate(LocalDateTime dateTime, String format) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
