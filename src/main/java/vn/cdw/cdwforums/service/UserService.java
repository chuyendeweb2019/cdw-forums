package vn.cdw.cdwforums.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.cdw.cdwforums.entity.Role;
import vn.cdw.cdwforums.entity.User;
import vn.cdw.cdwforums.reponsitory.RoleRepository;
import vn.cdw.cdwforums.reponsitory.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private static final long ID_ROLE_FOR_NEW_USER = 2;

	private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản : " + username);
        }
        return user;
	}
	
	public void signupUser(User user) {
		
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> oUserRole = roleRepository.findById(ID_ROLE_FOR_NEW_USER);
        Role userRole = oUserRole.get();
        user.setRoles(Collections.singleton(userRole));
        
        userRepository.save(user);
    }

    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = null;
        if (userDetails instanceof User) {
            user = (User) userDetails;
        }
        return user;
    }

    public boolean isCurrentUserId(Long id) {
        User user = getCurrentUser();
        return (Objects.nonNull(user) && (user.getId().equals(id)));
    }

    public Long getCurrentUserId() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.nonNull(object)) {
            try {
                UserDetails userDetails = (UserDetails) object;
                User user;
                if (userDetails instanceof User) {
                    user = (User) userDetails;
                    return user.getId();
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

}
