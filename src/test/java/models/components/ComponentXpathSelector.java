package models.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// annotation
@Retention(value = RetentionPolicy.RUNTIME)
// thoi diem thuc thi la khi nao
@Target(value =  {ElementType.TYPE})
// noi muon thuc thi la o dau
public @interface ComponentXpathSelector {
    String value();

}
