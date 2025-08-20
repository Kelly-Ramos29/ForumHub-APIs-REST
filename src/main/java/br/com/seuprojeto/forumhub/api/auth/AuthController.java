package br.com.seuprojeto.forumhub.api.auth;

import br.com.seuprojeto.forumhub.domain.user.*;
import br.com.seuprojeto.forumhub.security.JwtService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepo;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authManager;
  private final JwtService jwt;

  public record SignupDTO(@NotBlank String email, @NotBlank String password) {}
  public record LoginDTO(@NotBlank String email, @NotBlank String password) {}
  public record TokenDTO(String token) {}

  @PostMapping("/signup")
  public TokenDTO signup(@RequestBody SignupDTO dto) {
    var user = User.builder()
        .email(dto.email())
        .password(encoder.encode(dto.password()))
        .role(Role.USER)
        .build();
    userRepo.save(user);
    return new TokenDTO(jwt.generateToken(user.getEmail()));
  }

  @PostMapping("/login")
  public TokenDTO login(@RequestBody LoginDTO dto) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
    return new TokenDTO(jwt.generateToken(dto.email()));
  }
}
