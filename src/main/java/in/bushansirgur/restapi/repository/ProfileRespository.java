package in.bushansirgur.restapi.repository;

import in.bushansirgur.restapi.dto.ProfileDTO;
import in.bushansirgur.restapi.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRespository extends JpaRepository<ProfileEntity, Long> {


}
