package com.github.genehyde.ai_blocks.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammableEntityGoalParser {
    private static final Logger LOGGER = LogManager.getLogger();

    public static Goal parse(Entity thisRef, String data) {
        if (StringUtils.isBlank(data)) {
            return new NilGoal();
        }

        try {
            String[] args = data.split(" ");

            if (args.length > 0) {
                Class<?> cl = Class.forName(args[0]);

                if (Goal.class.isAssignableFrom(cl)) {
                    Constructor<?>[] cons = cl.getConstructors();
                    for (Constructor<?> constructor: cons) {
                        Parameter[] params = constructor.getParameters();
                        if (params.length == args.length - 1) {
                            Object[] instanceParams = new Object[params.length];
                            for (int i = 0; i < args.length - 1; i++) {
                                if (args[i + 1].equalsIgnoreCase("this")) {
                                    instanceParams[i] = thisRef;
                                } else if (args[i + 1].equalsIgnoreCase("null")) {
                                    instanceParams[i] = null;
                                } else if (args[i + 1].equalsIgnoreCase("true")) {
                                    instanceParams[i] = true;
                                } else if (args[i + 1].equalsIgnoreCase("false")) {
                                    instanceParams[i] = false;
                                } else if (String.class.isAssignableFrom(params[i].getType())) {
                                    instanceParams[i] = args[i + 1];
                                } else if (Integer.class.isAssignableFrom(params[i].getType())
                                        && NumberUtils.isCreatable(args[i + 1])) {
                                    instanceParams[i] = Integer.parseInt(args[i +1]);
                                } else if (Long.class.isAssignableFrom(params[i].getType())
                                        && NumberUtils.isCreatable(args[i + 1])) {
                                    instanceParams[i] = Long.parseLong(args[i +1]);
                                } else if (Float.class.isAssignableFrom(params[i].getType())
                                        && NumberUtils.isCreatable(args[i + 1])) {
                                    instanceParams[i] = Float.parseFloat(args[i +1]);
                                } else if (Double.class.isAssignableFrom(params[i].getType())
                                        && NumberUtils.isCreatable(args[i + 1])) {
                                    instanceParams[i] = Double.parseDouble(args[i +1]);
                                } else if (Class.class.isAssignableFrom(params[i].getType())
                                        && args[i + 1].contains(".class")) {
                                    String className = args[i + 1].trim().substring(0,
                                            args[i + 1].length() - ".class".length());
                                    instanceParams[i] = Class.forName(className);
                                }
                            }

                            return (Goal) constructor.newInstance(instanceParams);
                        }
                    }
                }
            }
        } catch(Exception ex) {
            LOGGER.error("Error parsing data: " + data, ex);
        }

        return new NilGoal();
    }

    public static List<Goal> parse(Entity thisRef, List<String> data) {
        if (data == null) {
            return Collections.emptyList();
        }

        return data.stream().map((dataStr) -> parse(thisRef, dataStr)).collect(Collectors.toList());
    }

    public static class NilGoal extends Goal {
        @Override
        public boolean shouldExecute() {
            return false;
        }
    }
}
