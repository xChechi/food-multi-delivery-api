package io.chechi.delivery.repository;

import io.chechi.delivery.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
