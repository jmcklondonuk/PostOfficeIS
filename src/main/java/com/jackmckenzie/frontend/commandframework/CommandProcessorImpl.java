package com.jackmckenzie.frontend.commandframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class CommandProcessorImpl implements CommandProcessor {
    private ApplicationContext applicationContext;

    private Map<Pattern, CommandBase> commandPatterns = new LinkedHashMap<>();

    @Autowired
    public CommandProcessorImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    private void initialize() {
        Set<Method> methods = AnnotationReflector.findAnnotatedMethods(getClass().getPackageName(), CommandRegExp.class);
        methods.forEach(method -> {
            String value = "^" + method.getAnnotation(CommandRegExp.class).value() + "$";
            Pattern pattern = Pattern.compile(value);
            commandPatterns.put(pattern, getCommandBean(method.getDeclaringClass()));
        });
    }

    @Override
    public boolean execute(String input) {
        for (Map.Entry<Pattern, CommandBase> entry : commandPatterns.entrySet()) {
            Pattern pattern = entry.getKey();
            CommandBase command = entry.getValue();

            if (pattern.matcher(input).matches()) {
                command.execute(input);
                return true;
            }
        }

        return false;
    }

    private CommandBase getCommandBean(Class<?> clazz) {
        return (CommandBase) applicationContext.getBeansOfType(clazz).values().toArray()[0];
    }
}
