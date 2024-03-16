package com.springboot.exam.data.repository;

import com.springboot.exam.data.entity.Signup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignupRepository extends JpaRepository<Signup,String> {
}
