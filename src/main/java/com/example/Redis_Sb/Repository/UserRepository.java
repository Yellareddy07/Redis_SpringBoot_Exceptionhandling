package com.example.Redis_Sb.Repository;

import com.example.Redis_Sb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
