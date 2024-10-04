package niesuv.facebookclone.user_service.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.dto.UpdateUserDto;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.entity.FacebookUser;
import niesuv.facebookclone.user_service.exception.CreateUserException;
import niesuv.facebookclone.user_service.exception.UserIdDontExists;
import niesuv.facebookclone.user_service.exception.UserNameExistException;
import niesuv.facebookclone.user_service.mapper.UserMapper;
import niesuv.facebookclone.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    public UUID createUser(CreateUserDTO userSignUpInfo)  {
        if (repository.existsByUserName(userSignUpInfo.userName())) {
            throw new UserNameExistException("Username have existed");
        }
        var id =  repository.save(mapper.toFaceBookUser(userSignUpInfo)).getId();
        if (id != null)
            return id;
        throw new CreateUserException("Cannot create user");
    }


    public UUID updateUser(UpdateUserDto dto) {
        Optional<FacebookUser> opUser = repository.findById(dto.id());
        if (opUser.isPresent()) {
            FacebookUser user = opUser.get();
            BeanCopier beanCopy;
            copyNonNullProperties(userMapper.toFaceBookUser(dto), user);
            return repository.save(user).getId();
        }
        throw new UserIdDontExists("User id does not exist");
    }



    public void copyNonNullProperties(FacebookUser source, FacebookUser target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Source or target cannot be null");
        }

        if (source.getUserName() != null) {
            target.setUserName(source.getUserName());
        }

        if (source.getEmail() != null) {
            target.setEmail(source.getEmail());
        }

        if (source.getFullName() != null) {
            target.setFullName(source.getFullName());
        }

        if (source.getAvtUrl() != null) {
            target.setAvtUrl(source.getAvtUrl());
        }

        if (source.getBackgroundUrl() != null) {
            target.setBackgroundUrl(source.getBackgroundUrl());
        }

    }
}
