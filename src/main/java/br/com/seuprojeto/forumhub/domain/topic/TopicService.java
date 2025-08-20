package br.com.seuprojeto.forumhub.domain.topic;

import br.com.seuprojeto.forumhub.api.dto.*;
import br.com.seuprojeto.forumhub.domain.course.CourseRepository;
import br.com.seuprojeto.forumhub.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

  private final TopicRepository topicRepo;
  private final CourseRepository courseRepo;

  @Transactional
  public TopicResponseDTO create(User author, TopicCreateDTO dto) {
    var course = courseRepo.findById(dto.courseId()).orElseThrow();
    var topic = Topic.builder()
        .title(dto.title())
        .message(dto.message())
        .author(author)
        .course(course)
        .status(TopicStatus.OPEN)
        .createdAt(LocalDateTime.now())
        .build();
    topicRepo.save(topic);
    return toDTO(topic);
  }

  @Transactional(readOnly = true)
  public List<TopicResponseDTO> list() {
    return topicRepo.findAll().stream().map(this::toDTO).toList();
  }

  @Transactional
  public TopicResponseDTO update(User requester, TopicUpdateDTO dto) {
    var topic = topicRepo.findById(dto.id()).orElseThrow();
    if (dto.title() != null) topic.setTitle(dto.title());
    if (dto.message() != null) topic.setMessage(dto.message());
    return toDTO(topic);
  }

  @Transactional
  public void delete(Long id) { topicRepo.deleteById(id); }

  private TopicResponseDTO toDTO(Topic t) {
    return new TopicResponseDTO(
        t.getId(), t.getTitle(), t.getMessage(),
        t.getStatus().name(),
        t.getCourse().getName(),
        t.getAuthor().getEmail()
    );
  }
}
