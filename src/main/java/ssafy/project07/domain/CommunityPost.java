package ssafy.project07.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class CommunityPost {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String content;
//    private String imageUrl;
    private String authorName;

    @ElementCollection
    private List<String> supplementTags;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "communityPost")
    private List<Comment> comments;


}