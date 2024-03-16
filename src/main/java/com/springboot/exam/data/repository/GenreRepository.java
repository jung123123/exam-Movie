package com.springboot.exam.data.repository;

import com.springboot.exam.data.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
