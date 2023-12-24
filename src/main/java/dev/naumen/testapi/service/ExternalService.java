package dev.naumen.testapi.service;

import dev.naumen.testapi.service.dto.ExternalUserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

    @Value("${external.service.url}")
    private String externalServiceUrl;

    private final RestTemplate restTemplate;

    public ExternalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalUserDto getUserByLogin(String login) {

        String url = externalServiceUrl + "/api/v1/user/" + login;
        return restTemplate.getForObject(url, ExternalUserDto.class);
    }

}
