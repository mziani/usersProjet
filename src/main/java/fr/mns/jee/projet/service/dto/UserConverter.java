package fr.mns.jee.projet.service.dto;

import fr.mns.jee.projet.dto.EditRequestDTO;
import fr.mns.jee.projet.dto.RegisterRequestDTO;
import fr.mns.jee.projet.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements IUserConverter {

    @Override
    public User convert(EditRequestDTO editRequestDTO) {
        User user = new User();

        user.setFirstName(editRequestDTO.getFirstName());
        user.setLastName(editRequestDTO.getLastName());
        user.setBirthDate(editRequestDTO.getBirthDate());
        user.setPhoneNumber(editRequestDTO.getPhoneNumber());
        user.setGender(editRequestDTO.getGender());
        user.setEmail(editRequestDTO.getEmail());
        user.setUsername(editRequestDTO.getUsername());

        if (editRequestDTO.getPassword().length() > 0) {
            user.setPassword(editRequestDTO.getPassword());
        }

        return user;
    }

    @Override
    public User convert(User user, EditRequestDTO editRequestDTO) {
        user.setFirstName(editRequestDTO.getFirstName());
        user.setLastName(editRequestDTO.getLastName());
        user.setBirthDate(editRequestDTO.getBirthDate());
        user.setPhoneNumber(editRequestDTO.getPhoneNumber());
        user.setGender(editRequestDTO.getGender());
        user.setEmail(editRequestDTO.getEmail());

        if (editRequestDTO.getPassword().length() > 0) {
            user.setPassword(editRequestDTO.getPassword());
        }

        return user;
    }
}
