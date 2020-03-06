package com.jackmckenzie.frontend.command;

import com.jackmckenzie.frontend.commandframework.CommandBase;
import com.jackmckenzie.frontend.commandframework.CommandRegExp;
import org.springframework.stereotype.Component;

@Component
public class QuitCommand implements CommandBase {
    @CommandRegExp("quit")
    @Override
    public void execute(String input) {
        System.exit(0);
    }
}
