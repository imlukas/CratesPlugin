package dev.imlukas.cratesplugin.item.actions;

import dev.imlukas.cratesplugin.item.actions.impl.CommandAction;
import dev.imlukas.cratesplugin.item.actions.impl.EconomyAction;
import dev.imlukas.cratesplugin.item.actions.impl.GiveAction;
import dev.imlukas.cratesplugin.item.actions.impl.MessageAction;

import java.util.Map;
import java.util.function.Function;

public class ActionRegistry {

    private static final Map<String, Function<String, ItemAction>> ACTION_MAP = Map.of(
            "MESSAGE", MessageAction::new,
            "COMMAND", CommandAction::new,
            "GIVE", GiveAction::new,
            "ECONOMY", EconomyAction::new);


    public static ItemAction getAction(String action) {

        Function<String, ItemAction> function = ACTION_MAP.get(action.substring(0, action.indexOf(":")));

        if (function == null) {
            throw new IllegalArgumentException("Unknown action: " + action);
        }

        return function.apply(action.substring(action.indexOf(":") + 1));
    }
}
