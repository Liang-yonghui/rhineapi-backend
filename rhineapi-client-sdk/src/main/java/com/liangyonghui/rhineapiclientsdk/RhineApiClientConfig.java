package com.liangyonghui.rhineapiclientsdk;


import com.liangyonghui.rhineapiclientsdk.client.RhineApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("rhineapi.client")
@Data
@ComponentScan
public class RhineApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public RhineApiClient rhineApiClient() {
        return new RhineApiClient(accessKey, secretKey);
    }
}
