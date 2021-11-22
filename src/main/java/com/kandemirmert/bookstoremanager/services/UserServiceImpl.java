package com.kandemirmert.bookstoremanager.services;

import com.kandemirmert.bookstoremanager.model.AddRoleToUserRequest;
import com.kandemirmert.bookstoremanager.model.Role;
import com.kandemirmert.bookstoremanager.model.User;
import com.kandemirmert.bookstoremanager.repository.RoleRepository;
import com.kandemirmert.bookstoremanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createAnuser(User user) {
        log.info("{} Yeni bir kullanıcı veritabanına kaydedildi.", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public Role saveARole(Role role) {
        log.info("{} yetki  veritabanına kaydedildi.", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(AddRoleToUserRequest roleToUserRequest) {
        log.info("{} adlı kullanıcıya {} yetkisi verildi", userRepository.findByUsername(roleToUserRequest.getUsername()), roleToUserRequest.getRolename());
        User user = userRepository.findByUsername(roleToUserRequest.getUsername());
        Role role = roleRepository.findByName(roleToUserRequest.getRolename());
        user.getRoles().add(role);
        userRepository.save(user);

    }

    @Override
    public void deleteUser(String username) {
        log.info("{} adlı kullanıcı veritabanından silidi.", userRepository.findByUsername(username).getUsername());
        userRepository.delete(userRepository.findByUsername(username));

    }

    @Override
    public User getUserByUsername(String username) {
        log.info("{} adlı kullanıcı veritabanından getirildi. ", userRepository.findByUsername(username).getUsername());
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Tüm kullanıcılar veritabanından getirilip listelendi.");
        return userRepository.findAll();
    }

    @Override
    public void changeARole(String username, String newRolename, String oldRoleName) {
        log.info("{} adlı kullanıcının yetkisi {} olarak değiştirildi.", userRepository.findByUsername(username).getUsername(), roleRepository.findByName(newRolename).getName());
        User user = userRepository.findByUsername(username);
        if (user.getRoles().contains(roleRepository.findByName(oldRoleName))) {
            user.getRoles().remove(roleRepository.findByName(oldRoleName));
            user.getRoles().add(roleRepository.findByName(newRolename));
            userRepository.save(user);
        }


    }

    @Override
    public void deleteAllUsers() {
        log.info("Tüm kullanıcılar sistemden silindi.");
        userRepository.deleteAll();

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if(user==null){
        log.error("{} adlı kullanıcı veritabanında kayıtlı değil",username);
        throw new UsernameNotFoundException("Kullanıcı veritabanında bulunamadı");
    }else{
        log.info("{} adlı kullanıcı veritabanından çağırıldı.",username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);


        }
}


