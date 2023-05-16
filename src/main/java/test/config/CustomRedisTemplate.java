package test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class CustomRedisTemplate<V> extends RedisTemplate<String, V> {
    public CustomRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory,
                               ObjectMapper objectMapper, Class<V> type) {
        super.setConnectionFactory(lettuceConnectionFactory);

        var keySerializer = new StringRedisSerializer();
        super.setKeySerializer(keySerializer);
        super.setHashKeySerializer(keySerializer);

        var valueSerializer = new Jackson2JsonRedisSerializer<>(type);
        valueSerializer.setObjectMapper(objectMapper);
        super.setValueSerializer(valueSerializer);
        super.setHashValueSerializer(valueSerializer);
    }
}
