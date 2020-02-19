package se.vbgt.test.bb.security;

import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher.Junction.AbstractBase;
import se.vbgt.test.bb.domain.Anno;

import java.util.Objects;

public class IsAnnoMatcher<V extends TypeDescription> extends AbstractBase<V> {

    @Override
    public boolean matches(V typeDefinitions) {
        return typeDefinitions.getDeclaredMethods()
                              .stream()
                              .anyMatch(o -> isAnnoAnnotatedMethod(o));

    }


    private boolean isAnnoAnnotatedMethod(Object declaredMethod) {
        if (declaredMethod instanceof MethodDescription) {
            MethodDescription methodDescription = (MethodDescription) declaredMethod;
            return methodDescription.getDeclaredAnnotations()
                                    .stream()
                                           .anyMatch(a -> isAnnoAnnotation(a));
        }
        return false;
    }

    private static boolean isAnnoAnnotation(AnnotationDescription a) {
        return Objects.equals(
            a.getAnnotationType().getName(),
            Anno.class.getName());
    }
}
