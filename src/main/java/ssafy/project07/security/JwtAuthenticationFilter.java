package ssafy.project07.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ssafy.project07.domain.user.User;
import ssafy.project07.repository.user.UserRepository;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtTokenProvider.validateToken(token)) {
                String email = jwtTokenProvider.getEmailFromToken(token);

                System.out.println("🟡 추출된 이메일: " + email); // ✅ 추가

                try {
                    User user = userRepository.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("❌ 해당 이메일의 유저가 존재하지 않습니다: " + email));

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(user, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(auth);

                } catch (Exception e) {
                    System.out.println("🔴 JWT 필터 에러: " + e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "사용자 인증 실패");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
