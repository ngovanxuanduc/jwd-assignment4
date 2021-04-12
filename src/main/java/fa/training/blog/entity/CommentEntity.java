package fa.training.blog.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tbl_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "content")
	private String content;

	@Column(name = "status")
	private int status;

	@Column(name = "author")
	private String author;

	@Column(name = "url")
	private String url;

	@Column(name = "email")
	private String email;

	@CreationTimestamp
	@Column(name = "create_time", updatable = false)
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "post_id")
	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
	@ToString.Exclude // Khong sử dụng trong toString()
	private PostEntity post;
}
