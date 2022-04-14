package com.bootcamp.microservicemeetup.repository;

import com.bootcamp.microservicemeetup.model.entity.Registration;
import com.bootcamp.microservicemeetup.utils.FakeObjectCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class RegistrationRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    RegistrationRepository repository;

    @Test
    @DisplayName("Teste para verificar se o registro foi criado")
    public void findByRegistrationTest() {
        //given
        Registration registration_Class_attribute = FakeObjectCreator.createRegistration("323");
        entityManager.persist(registration_Class_attribute);
        //when
        Optional<Registration> foundRegistration = repository.findByRegistration(registration_Class_attribute.getRegistration());
        //then
        assertThat(foundRegistration.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve retornar true quando existe um registro já criado.")
    public void returnTrueWhenRegistrationExists() {

        String registration = "123";

        Registration registration_Class_attribute = FakeObjectCreator.createRegistration(registration);
        entityManager.persist(registration_Class_attribute);

        boolean exists = repository.existsByRegistration(registration);

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando não existe um registration_attribute com um registro já criado.")
    public void returnFalseWhenRegistrationAttributeDoesntExists() {

        String registration = "123";

        boolean exists = repository.existsByRegistration(registration);

        assertThat(exists).isFalse();

    }

    @Test
    @DisplayName("Deve obter um registro por id")
    public void findByIdTest() {

        Registration registration_Class_attribute = FakeObjectCreator.createRegistration("323");
        entityManager.persist(registration_Class_attribute);

        Optional<Registration> foundRegistration = repository
                .findById(registration_Class_attribute.getId());

        assertThat(foundRegistration.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Deve salvar um registro")
    public void saveRegistrationTest() {

        Registration registration_Class_attribute = FakeObjectCreator.createRegistration("323");

        Registration savedRegistration = repository.save(registration_Class_attribute);

        assertThat(savedRegistration.getId()).isNotNull();

    }

    @Test
    @DisplayName("Deve excluir e registro da base de dados")
    public void deleteRegistation() {

        Registration registration_Class_attribute = FakeObjectCreator.createRegistration("323");
        entityManager.persist(registration_Class_attribute);

        Registration foundRegistration = entityManager
                .find(Registration.class, registration_Class_attribute.getId());
        repository.delete(foundRegistration);

        Registration deleteRegistration = entityManager
                .find(Registration.class, registration_Class_attribute.getId());

        assertThat(deleteRegistration).isNull();

    }

    @Test
    @DisplayName("Deve atualizar um registro")
    public void updateRegistration() {

        Registration registration_Class_attribute = FakeObjectCreator.createRegistration("323");
        entityManager.persist(registration_Class_attribute);

        Registration foundRegistration = entityManager
                .find(Registration.class, registration_Class_attribute.getId());

        foundRegistration.setRegistration("123");

        Registration updatedRegistration = repository.save(foundRegistration);

        assertThat(updatedRegistration.getRegistration()).isEqualTo("123");

    }


}
