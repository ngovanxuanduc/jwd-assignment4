package fa.training.blog.entity;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tbl_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

//	@Column(name = "tags")
//	private String tags;

	@Column(name = "status")
	private int status;

	@CreationTimestamp
	@Column(name = "create_time", updatable = false)
	private Date createDate;

	@UpdateTimestamp
	@Column(name = "update_time")
	private Date updateDate;

	@ManyToOne()
	@JoinColumn(name = "author_id")
	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
	@ToString.Exclude // Khong sử dụng trong toString()
	private UserEntity author;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
	@ToString.Exclude // Khong sử dụng trong toString()
	private Collection<Comment> comments;
	
	@ManyToMany(mappedBy = "posts", fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Collection<TagEntity> tags;
	
}
