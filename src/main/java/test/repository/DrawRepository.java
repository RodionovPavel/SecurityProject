package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.model.Draw;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DrawRepository extends JpaRepository<Draw, UUID> {

    Optional<Draw> findById(UUID drawId);

    List<Draw> getAllByDrawDateBefore(LocalDateTime date);

}
