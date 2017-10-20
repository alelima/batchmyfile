package org.nitrox.batchmyfile.layout;

import org.nitrox.batchmyfile.file.FilePartType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LayoutPart {
    Class<? extends Enum<?>> filePartClass();

    String filePartName();
}
