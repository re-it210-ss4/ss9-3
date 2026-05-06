package validation;
import jakarta.validation.*;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String firstObj = BeanUtils.getProperty(value, firstFieldName);
            String secondObj = BeanUtils.getProperty(value, secondFieldName);
            // Chống bẫy Null: Nếu cả 2 null là khớp, nếu 1 trong 2 null là không khớp
            return (firstObj == null && secondObj == null) || (firstObj != null && firstObj.equals(secondObj));
        } catch (Exception e) { return false; }
    }
}