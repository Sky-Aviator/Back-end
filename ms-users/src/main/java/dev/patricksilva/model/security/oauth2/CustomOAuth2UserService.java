 package dev.patricksilva.model.security.oauth2;

import java.util.Collections;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import dev.patricksilva.model.dtos.UserDto;
import dev.patricksilva.model.entities.User;
import dev.patricksilva.model.repository.UserRepository;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    private final MongoTemplate mongoTemplate;
 
    public CustomOAuth2UserService(UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oauthUserRequest) throws RuntimeException {
        // Obtenha os detalhes do usuário do provedor OAuth2
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User user = delegate.loadUser(oauthUserRequest);
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String token = ((OAuth2AccessToken) oauthUserRequest.getAccessToken()).getTokenValue();

        // Salvando as informações do usuário no banco de dados
		Optional<UserDto> userEntityOptional = userRepository.findByEmail(email);
		if (userEntityOptional.isEmpty()) {
			User userEntity = new User();
			userEntity.setEmail(email);
			userEntity.setFirstName(name);
			userEntity.setToken(token);
			userRepository.save(userEntity);
		}

        // Retorna um usuário autenticado
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), user.getAttributes(), "email");
    }
}