package vn.cdw.cdwforums.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	

    private static final long ID_ROLE_FOR_NEW_USER = 1L;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return user;
    }
    public boolean loadByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
        	 return false;
        }
        return true;
    }

    public boolean loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            return false;
        }
        return true;
    }

    public void signupUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findById(ID_ROLE_FOR_NEW_USER).orElse(new Role());
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

    @SuppressWarnings("unchecked")
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
    public List<User> findAll() {
    	List<User> list = new ArrayList<User>();
    	    for (User item : userRepository.findAll()) {
    	        list.add(item);
    	    }
    	return list;
    }

}
