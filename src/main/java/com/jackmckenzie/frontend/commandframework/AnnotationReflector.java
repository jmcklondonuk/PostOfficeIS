package com.jackmckenzie.frontend.commandframework;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class AnnotationReflector {
    public static Set<Method> findAnnotatedMethods(String packageName, Class<? extends Annotation> annotation) {
        ConfigurationBuilder builder = new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new MethodAnnotationsScanner());

        Reflections reflections = new Reflections(builder);
        return reflections.getMethodsAnnotatedWith(annotation);
    }
}
