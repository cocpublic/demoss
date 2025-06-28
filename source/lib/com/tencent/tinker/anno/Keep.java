package com.tencent.tinker.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used by Tinker to indicate that a class, method, field, etc.,
 * should be kept by ProGuard and not obfuscated or removed.
 * This is crucial for elements that are accessed via reflection or other
 * means where the original name is required.
 */
@Retention(RetentionPolicy.CLASS)
@Target({
    ElementType.PACKAGE,
    ElementType.TYPE,
    ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR,
    ElementType.METHOD,
    ElementType.FIELD
})
public @interface Keep {
    // This annotation interface typically has no members.
    // Its presence is what matters.
}
