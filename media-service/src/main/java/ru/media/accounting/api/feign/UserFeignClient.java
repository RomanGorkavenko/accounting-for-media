package ru.media.accounting.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.media.accounting.dto.UserRequest;
import ru.media.accounting.dto.UserResponse;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/api/user/{username}")
    ResponseEntity<UserResponse> findByUsername(@PathVariable("username") String username);

    @PostMapping("/api/user")
    ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest);

    @GetMapping("/api/user/mail/{email}")
    ResponseEntity<UserResponse> findByEmail(@PathVariable("email") String email);

}
