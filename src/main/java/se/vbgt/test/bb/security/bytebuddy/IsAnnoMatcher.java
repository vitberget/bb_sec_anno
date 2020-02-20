package se.vbgt.test.bb.security.bytebuddy;

import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription.InDefinedShape;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher.Junction.AbstractBase;
import se.vbgt.test.bb.security.Anno;

import java.util.Objects;

public class IsAnnoMatcher<V extends TypeDescription> extends AbstractBase<V> {

    @Override
    public boolean matches(V typeDefinitions) {
        return typeDefinitions.getDeclaredMethods()
                              .stream()
                              .anyMatch(o -> isAnnoAnnotatedMethod(o));
    }

    private boolean isAnnoAnnotatedMethod(InDefinedShape shape) {
        return shape.getDeclaredAnnotations()
                    .stream()
                    .anyMatch(a -> isAnnoAnnotation(a));
    }

    private static boolean isAnnoAnnotation(AnnotationDescription annotation) {
        return Objects.equals(
            annotation.getAnnotationType().getName(),
            Anno.class.getName());
    }
}
