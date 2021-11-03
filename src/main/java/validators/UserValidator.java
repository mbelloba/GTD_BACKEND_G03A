package validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capgemini.model.User;

/**
 * Class for Validate Users
 * @author gtd-g03
 *
 */
@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		//Comprobamos que los campos nombre y apellido no sean vacíos o nulos
		ValidationUtils.rejectIfEmpty(errors, "email","user.email", "El campo nombre no puede estar vacío.");
		ValidationUtils.rejectIfEmpty(errors, "login","user.login", "El campo apellido no puede estar vacío.");
		ValidationUtils.rejectIfEmpty(errors, "password","user.password", "El campo apellido no puede estar vacío.");
		
		//Comprobamos que el campo nombre no contenga dígitos
		char[] chainEmail = user.getEmail().toCharArray();
		for(int i=0; i < chainEmail.length; i++) {
			if(Character.isDigit(chainEmail[i])) {
				errors.rejectValue("email", "user.email","El campo nombre no puede contener dígitos.");
			}
		}
		
		//Comprobamos que el campo apellido no contenga dígitos
		char[] chainLogin = user.getLogin().toCharArray();
		for(char character: chainLogin) {
			if(Character.isDigit(character)) {
				errors.rejectValue("login", "user.login","El campo apellido no puede contener dígitos.");
			}
		}
		
		//Comprobamos que el campo password no contenga dígitos
		char[] chainPassword = user.getLogin().toCharArray();
		for(char character: chainPassword) {
			if(Character.isDigit(character)) {
				errors.rejectValue("login", "user.password","El campo password no puede contener dígitos.");
			}
		}
		
	}

}
