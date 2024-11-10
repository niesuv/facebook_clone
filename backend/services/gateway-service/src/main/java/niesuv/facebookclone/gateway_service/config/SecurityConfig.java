package niesuv.facebookclone.gateway_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity) {
        try {
            return httpSecurity.authorizeExchange(
                            http -> {
                                http.anyExchange().permitAll();
                            })
                    .csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
