package com.practice.chesswebapp.security;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService  {
//    implements UserDetailsService

//    private UserRepository userRepository;
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("No user found with username: " + username);
//        }
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(), user.getPassword(), enabled, accountNonExpired,
//                credentialsNonExpired, accountNonLocked, mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
//        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//        return mapRoles;
//    }
}
