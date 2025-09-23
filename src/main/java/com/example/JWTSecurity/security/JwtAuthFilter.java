import com.example.JWTSecurity.repository.UserRepository;
import com.example.JWTSecurity.security.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;

  public JwtAuthFilter(JwtUtil jwtUtil, UserRepository userRepository){
      this.jwtUtil = jwtUtil;
      this.userRepository = userRepository;
  }
}