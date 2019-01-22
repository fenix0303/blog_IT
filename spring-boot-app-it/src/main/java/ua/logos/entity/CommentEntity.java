package ua.logos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity extends  BaseEntity{
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(name="comment_post", joinColumns = @JoinColumn(name="comment_id"), inverseJoinColumns = @JoinColumn(name="post_id"))
    private List<PostEntity> post;




}
