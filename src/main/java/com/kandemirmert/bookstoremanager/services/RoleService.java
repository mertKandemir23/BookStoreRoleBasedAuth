package com.kandemirmert.bookstoremanager.services;

import com.kandemirmert.bookstoremanager.model.Role;
import com.kandemirmert.bookstoremanager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void deleteAllRoles(){
        log.info("Tüm yetkilendirmeler veritabanından silindi.");
        roleRepository.deleteAll();
    }
    public  void deleteRole(Long id){
        log.info("{} numaralı yetki veritabanından kaldırıldı",id);

        roleRepository.deleteById(id);
    }
}
