package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.model.Result;

import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<Result, UUID> {
}
