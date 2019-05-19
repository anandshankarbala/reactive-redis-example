package com.example.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveKeyCommands;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.ReactiveStringCommands;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.entity.Employee;

import javax.annotation.PreDestroy;

@Configuration
public class RedisConfig {

    @Autowired
    RedisConnectionFactory factory;

    @Bean
    public ReactiveRedisTemplate<String, Employee> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Employee> builder = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, Employee> context = builder.value(serializer)
				.hashKey(serializer)
				.hashValue(serializer)
//				.value(serializer)
            .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }

	/*
	 * @Bean public ReactiveRedisTemplate<String, String>
	 * reactiveRedisTemplateString(ReactiveRedisConnectionFactory connectionFactory)
	 * { return new ReactiveRedisTemplate<>(connectionFactory,
	 * RedisSerializationContext.string()); }
	 * 
	 * @Bean public ReactiveKeyCommands keyCommands(final
	 * ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) { return
	 * reactiveRedisConnectionFactory.getReactiveConnection() .keyCommands(); }
	 * 
	 * @Bean public ReactiveStringCommands stringCommands(final
	 * ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) { return
	 * reactiveRedisConnectionFactory.getReactiveConnection() .stringCommands(); }
	 */

    @PreDestroy
    public void cleanRedis() {
        factory.getConnection()
            .flushDb();
    }
    
    /*
    @EnableWebFluxSecurity
    class SecurityConfiguration {
        @Bean
        UserDetailsRepository userDetailsRepository() {
            return new MapUserDetailsRepository(user("rob").build(), user("josh").roles("USER","ADMIN").build());
        }
        private User.UserBuilder user(String username) {
            return User.withUsername(username).password("password").roles("USER");
        }
        @Bean
        SecurityWebFilterChain springSecurity(HttpSecurity http) {
            return http
                    .authorizeExchange()
                        .pathMatchers("/users/me").authenticated()
                        .pathMatchers("/users/{username}").access((auth,context) ->
                            auth
                                    .map( a-> a.getName().equals(context.getVariables().get("username")))
                                    .map(AuthorizationDecision::new)
                        )
                        .anyExchange().hasRole("ADMIN")
                        .and()
                    .build();
        }
    }*/
}
