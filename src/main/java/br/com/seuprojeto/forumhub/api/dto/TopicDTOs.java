package br.com.seuprojeto.forumhub.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreateDTO(
  @NotBlank String title,
  @NotBlank String message,
  @NotNull Long courseId
) {}

public record TopicUpdateDTO(
  @NotNull Long id,
  String title,
  String message
) {}

public record TopicResponseDTO(
  Long id, String title, String message, String status, String course, String author
) {}
