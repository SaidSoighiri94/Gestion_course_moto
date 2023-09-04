package soighiri.com.coursemoto.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityUserDetailsSerivce {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
