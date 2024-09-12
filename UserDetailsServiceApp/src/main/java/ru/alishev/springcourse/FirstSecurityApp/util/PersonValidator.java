package ru.alishev.springcourse.FirstSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.services.MyUserDetailsService;

@Component
public class PersonValidator implements Validator {

    private MyUserDetailsService personDetailsService;

    @Autowired
    public PersonValidator(MyUserDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    // Заменить!!!!!!!!!!!!!!
    @Override
    public void validate(Object o, Errors errors) {
        User person = (User) o;

        try {
            personDetailsService.loadUserByUsername(person.getUsername());
        }catch (UsernameNotFoundException e) {
            return; // Позьзователь не найден
        }
        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
    }

}
