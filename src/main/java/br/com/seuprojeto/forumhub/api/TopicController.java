package br.com.seuprojeto.forumhub.api;

import br.com.seuprojeto.forumhub.api.dto.*;
import br.com.seuprojeto.forumhub.domain.topic.TopicService;
import br.com.seuprojeto.forumhub.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/topics") @RequiredArgsConstructor
public class TopicController {

  private final TopicService service;

  @PostMapping
  public TopicResponseDTO create(@AuthenticationPrincipal User user, @RequestBody @Valid TopicCreateDTO dto) {
    return service.create(user, dto);
  }

  @GetMapping
  public List<TopicResponseDTO> list() {
    return service.list();
  }

  @PutMapping
  public TopicResponseDTO update(@AuthenticationPrincipal User user, @RequestBody @Valid TopicUpdateDTO dto) {
    return service.update(user, dto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
