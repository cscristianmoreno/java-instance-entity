package annotations.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import annotations.IsRow;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@IsRow
public @interface PrimaryKey {
    
}
