/**
 * 
 */
package in.techshine.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.techshine.beans.Role;
import in.techshine.beans.User;
import in.techshine.repository.RoleRepository;
import in.techshine.repository.UserRepository;

/**
 * @author anup
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRoles(new HashSet<Role>(roleRepository.findAll()));
        HashSet<Role> role = new HashSet<>();
        role.add(new Role(2L, "ROLE_USER", null));
        user.setRoles(role);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
