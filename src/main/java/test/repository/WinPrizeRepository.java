package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.model.WinPrize;

import java.util.UUID;

@Repository
public interface WinPrizeRepository extends JpaRepository<WinPrize, UUID> {
}