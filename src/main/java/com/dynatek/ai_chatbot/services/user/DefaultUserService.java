package com.dynatek.ai_chatbot.services.user;

import com.dynatek.ai_chatbot.models.ChangePasswordRequest;
import com.dynatek.ai_chatbot.models.User;
import com.dynatek.ai_chatbot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@AllArgsConstructor
@Service
public class DefaultUserService
        implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(Principal currentUser, ChangePasswordRequest changePasswordRequest) {
        User user = (User) ((UsernamePasswordAuthenticationToken) currentUser).getPrincipal();
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!changePasswordRequest.getNewPassword()
                                  .equals(changePasswordRequest.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }
}
