package com.cursus.cursus.Service;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    KafkaProperties.Admin ajouterAdmin(KafkaProperties.Admin admin);
    KafkaProperties.Admin modifierAdmin(KafkaProperties.Admin admin);
    List<KafkaProperties.Admin> GetAdmin();
    void supprimerAdmin(Long id);


    Optional<KafkaProperties.Admin> GetAdminById(Long id);
}
