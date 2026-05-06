package validation;
import jakarta.validation.*;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ ElementType.TYPE }) // Áp dụng cho cấp độ Class
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {
    String message() default "Dữ liệu không khớp";
    String first();
    String second();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}