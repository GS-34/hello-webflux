package com.webflux.security;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SecuredProfileController {

    private final ProfileService profileService;

    public SecuredProfileController(ProfileService service) {
        profileService = service;
    }

    @GetMapping("/profiles")
    public Mono<Profile> getProfile() {
        return ReactiveSecurityContextHolder
            .getContext()
            .map(c ->{
                return  c.getAuthentication();
            })
            .flatMap(auth -> profileService.getByUser(auth.getName()));
    }

    @GetMapping("/")
    public Mono<String> index() {
        return ReactiveSecurityContextHolder
            .getContext()
            .map(SecurityContext::getAuthentication)
            .flatMap(auth -> Mono.just("Welcome   " + auth.getName() + "!!!"));
    }
}
