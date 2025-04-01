package in.bushansirgur.restapi.service.impl;

import in.bushansirgur.restapi.dto.ProfileDTO;
import in.bushansirgur.restapi.entity.ProfileEntity;
import in.bushansirgur.restapi.repository.ProfileRespository;
import in.bushansirgur.restapi.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRespository profileRespository;
    private final ModelMapper modelMapper;

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO){
        ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        //Call the repository method
        profileEntity = profileRespository.save(profileEntity);
        log.info("Printing the profile entity details {}", profileEntity);
        return mapToProfileDTO(profileEntity);
    }

    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDTO.class);
    }

    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileEntity.class);
    }
}
