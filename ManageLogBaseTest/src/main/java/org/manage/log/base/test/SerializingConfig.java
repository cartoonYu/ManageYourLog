package org.manage.log.base.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cartoon
 * @since 2022/11/24 14:06
 */
@Configuration
public class SerializingConfig {


    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper =  new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
}
