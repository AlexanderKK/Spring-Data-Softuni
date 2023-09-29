package bg.softuni.springdataintroexercise.models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    int minLength();

    int maxLength();

    boolean containsDigit();

    boolean containsLowercase();


}
