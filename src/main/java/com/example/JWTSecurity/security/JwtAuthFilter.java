import com.example.JWTSecurity.repository.UserRepository;
import com.example.JWTSecurity.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;

  public JwtAuthFilter(JwtUtil jwtUtil, UserRepository userRepository){
      this.jwtUtil = jwtUtil;
      this.userRepository = userRepository;
  }

  @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws SecurityException, IOException{
      final String authHeader = request.getHeader("Authorization");
      String email = null;
      String token = null;
  }
}