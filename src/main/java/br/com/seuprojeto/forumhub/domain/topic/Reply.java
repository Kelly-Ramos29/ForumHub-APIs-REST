package br.com.seuprojeto.forumhub.domain.topic;

import br.com.seuprojeto.forumhub.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="replies")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reply {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false)
  private Topic topic;

  @ManyToOne(optional=false)
  private User author;

  @Column(nullable=false, columnDefinition="text")
  private String message;

  @Column(nullable=false)
  private LocalDateTime createdAt;
}
