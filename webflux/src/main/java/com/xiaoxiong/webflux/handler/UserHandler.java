package com.xiaoxiong.webflux.handler;

import com.xiaoxiong.webflux.domain.User;
import com.xiaoxiong.webflux.repository.UserRepository;
import com.xiaoxiong.webflux.utils.CheckUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  12:16
 */
@Component
public class UserHandler {

    private UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(this.userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(u -> {
            CheckUtils.checkUserName(u.getName());
            return ok().contentType(APPLICATION_JSON)
                    .body(userRepository.save(u), User.class);
        });
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {
        return createUser(request);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.userRepository.findById(id)
                .flatMap(user -> this.userRepository.delete(user).then(ok().build()))
                .switchIfEmpty(notFound().build());
    }

}
