package com.acledabankplc.utils;


import com.acledabankplc.config.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationUtils {

    public UserRequestDTO getUserRequestDTO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        String userId = null;
        String userRole = null;

        if (principal instanceof User) {
            userId = String.valueOf(((User) principal).getId());
        }

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            userRole = authority.getAuthority();
            break; // Assuming the user has only one role
        }

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername(username);
        userRequestDTO.setUserRole(userRole);
        userRequestDTO.setUserId(userId);

        return userRequestDTO;
    }
}
