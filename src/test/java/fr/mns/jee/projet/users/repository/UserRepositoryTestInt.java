package fr.mns.jee.projet.users.repository;

import fr.mns.jee.projet.model.Gender;
import fr.mns.jee.projet.model.User;
import fr.mns.jee.projet.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTestInt {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByUsername() {
        //given
        User user = new User();
        user.setFirstName("FirstName-Test");
        user.setLastName("LastName-Test");
        user.setGender(Gender.FEMALE);
        user.setBirthDate(LocalDate.MIN);
        user.setPhoneNumber("0123456789");
        user.setUsername("Username-Test");
        entityManager.persist(user);

        //when
        User foundUser = repository.findByUsername("Username-Test");

        //then
        assertEquals("FirstName-Test", foundUser.getFirstName());
    }

    @Test
    public void testFindByBirthDateAfter() {
        //given
        User user1 = new User();
        user1.setFirstName("FirstName-Test1");
        user1.setLastName("LastName-Test1");
        user1.setGender(Gender.FEMALE);
        user1.setBirthDate(LocalDate.of(2000, 5, 1));
        user1.setUsername("test1");
        entityManager.persist(user1);

        User user2 = new User();
        user2.setFirstName("FirstName-Test2");
        user2.setLastName("LastName-Test2");
        user2.setGender(Gender.MALE);
        user2.setBirthDate(LocalDate.of(2004, 7, 31));
        user2.setUsername("test2");
        entityManager.persist(user2);

        LocalDate searchDate = LocalDate.of(1999, 12, 31);

        //when
        List<User> foundUsers = repository.findByBirthDateAfter(searchDate);

        //then
        assertTrue(foundUsers.size() == 2);
    }

    @Test
    public void testFindByGender() {
        //given
        User user1 = new User();
        user1.setFirstName("FirstName-Test1");
        user1.setLastName("LastName-Test1");
        user1.setGender(Gender.FEMALE);
        user1.setBirthDate(LocalDate.of(2000, 5, 1));
        user1.setUsername("test1");
        entityManager.persist(user1);

        User user2 = new User();
        user2.setFirstName("FirstName-Test2");
        user2.setLastName("LastName-Test2");
        user2.setGender(Gender.MALE);
        user2.setBirthDate(LocalDate.of(2004, 7, 31));
        user2.setUsername("test2");
        entityManager.persist(user2);

        //when
        List<User> foundUsers = repository.findByGender(Gender.FEMALE);

        //then
        assertTrue(foundUsers.size() == 1);
        assertEquals(foundUsers.get(0).getFirstName(), "FirstName-Test1");
    }

    @Test
    public void testGetByOrderByBirthDateAsc() {
        //given
        User user1 = new User();
        user1.setFirstName("FirstName-Test1");
        user1.setLastName("LastName-Test1");
        user1.setGender(Gender.FEMALE);
        user1.setBirthDate(LocalDate.of(2000, 5, 1));
        user1.setUsername("test1");
        entityManager.persist(user1);

        User user2 = new User();
        user2.setFirstName("FirstName-Test2");
        user2.setLastName("LastName-Test2");
        user2.setGender(Gender.MALE);
        user2.setBirthDate(LocalDate.of(1998, 7, 31));
        user2.setUsername("test2");
        entityManager.persist(user2);

        //when
        List<User> foundUsers = repository.getByOrderByBirthDateAsc();

        //then
        assertEquals(foundUsers.get(0).getFirstName(), "FirstName-Test2");
    }

}
