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
@Table(name = "post")
public class PostEntity extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;
    @Column(name = "date_of_create", nullable = false, columnDefinition = "date")
    private String dateOfCreate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;


    @ManyToMany
    @JoinTable(name="post_tag", joinColumns = @JoinColumn(name="post_id"), inverseJoinColumns = @JoinColumn(name="tag_id"))
    private List<TagEntity> tag;

}
