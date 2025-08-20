package br.com.seuprojeto.forumhub.domain.topic;

import br.com.seuprojeto.forumhub.domain.user.User;
import br.com.seuprojeto.forumhub.domain.course.Course;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="topics")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Topic {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private String title;

  @Column(nullable=false, columnDefinition="text")
  private String message;

  @ManyToOne(optional=false)
  private User author;

  @ManyToOne(optional=false)
  private Course course;

  @Column(nullable=false)
  private LocalDateTime createdAt;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private TopicStatus status;

  @OneToMany(mappedBy="topic", cascade=CascadeType.ALL, orphanRemoval=true)
  private List<Reply> replies = new ArrayList<>();
}
