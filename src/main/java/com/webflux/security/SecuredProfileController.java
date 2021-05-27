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

    /**
     * 리액티브 스프링 보안 모듈에서 SecurityContext 에 접근하기 위해
     * ReactiveSecurityContextHolder 라는 새로운 클래스를 사용
     */
    @GetMapping("/")
    public Mono<String> index() {
        return ReactiveSecurityContextHolder
            .getContext()//SecurityContext Get
            .map(SecurityContext::getAuthentication)//인증
            .flatMap(auth -> Mono.just("Welcome   " + auth.getName() + "!!!"));
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
}
