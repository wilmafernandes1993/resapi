package in.bushansirgur.restapi.repository;

import in.bushansirgur.restapi.dto.ProfileDTO;
import in.bushansirgur.restapi.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRespository extends JpaRepository<ProfileEntity, Long> {
      Optional<ProfileEntity> findByEmail(String email);

      Boolean existsByEmail(String email);
}
