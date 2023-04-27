package test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import test.dto.ClientOtp;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final LettuceConnectionFactory factory;
    private final ObjectMapper mapper;

    @Bean
    public CustomRedisTemplate<ClientOtp> clientOtpTemplate() {
        return new CustomRedisTemplate<>(factory, mapper, ClientOtp.class);
    }

}

