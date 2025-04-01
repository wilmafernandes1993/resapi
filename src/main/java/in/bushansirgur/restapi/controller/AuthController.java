package in.bushansirgur.restapi.controller;

import in.bushansirgur.restapi.dto.ProfileDTO;
import in.bushansirgur.restapi.io.ProfileRequest;
import in.bushansirgur.restapi.io.ProfileResponse;
import in.bushansirgur.restapi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final ProfileService profileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        log.info("API /register is called {}", profileRequest);
        ProfileDTO profileDTO = mapToProfileDTO(profileRequest);
        profileDTO = profileService.createProfile(profileDTO);
        log.info("Printing the profile dto details {}", profileDTO);
        return mapToProfileResponse(profileDTO);
    }

    private ProfileDTO mapToProfileDTO(ProfileRequest profileRequest){
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO){
       return modelMapper.map(profileDTO, ProfileResponse.class);

    }
}
