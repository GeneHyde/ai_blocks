package com.github.genehyde.ai_blocks.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleProgrammableEntityData {
    private final Map<Integer, List<String>> goals;

    public SimpleProgrammableEntityData(Map<Integer, List<String>> goals) {
        this.goals = goals;
    }

    public SimpleProgrammableEntityData(Dynamic<?> dynamic) {
        this(dynamic.get("goals").asMap((item) -> Integer.parseInt(item.asString("99")),
                (item) -> item.asList((goal) -> goal.asString().orElse(""))
        ));
    }

    public Map<Integer, List<String>> getGoals() {
        return goals;
    }

    public <T> T serialize(DynamicOps<T> dynamicOps) {
        Map<T, T> serializedMap = this.goals.keySet().stream()
                .collect(Collectors.toMap(
                        dynamicOps::createInt,
                        (key) -> dynamicOps.createList(this.goals.get(key).stream()
                                .map(dynamicOps::createString)
                )));
        return dynamicOps.createMap(ImmutableMap.of(dynamicOps.createString("goals"),
                dynamicOps.createMap(serializedMap)));
    }
}
