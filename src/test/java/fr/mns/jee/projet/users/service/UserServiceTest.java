package fr.mns.jee.projet.users.service;

import fr.mns.jee.projet.model.User;
import fr.mns.jee.projet.repository.UserRepository;
import fr.mns.jee.projet.service.UserService;
import net.bytebuddy.ClassFileVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)

//@SpringBootTest
public class UserServiceTest {
    @Mock

    private UserRepository userRepository;

    @InjectMocks

    private UserService userService;
    private User user;


    @BeforeEach

    void init(){ //
        user=new User();
        user.setId(1l);
        user.setUsername("Margo");

        userService.save(user);

        when(userService.findByUsername("Margo")).thenReturn(user);
        when(userService.findById(1l)).thenReturn(user);


    }
    @Test

    public void testfindByUsername(){

        User u = userService.findByUsername("Margo");
        assertNotNull(u);
        assertEquals( "Margo", u.getUsername());


    }

    @Test
    public void testfindById(){

        User u=userService.findById(1l);
        assertNotNull(u);
        assertEquals(1l, u.getId());
    }

    @Test
    public void testdeleteById(){

        final Long id = 1L;
        userService.deleteById(1L);
        Mockito.verify(userRepository).deleteById(1L);
    }






}