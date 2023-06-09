package hotciv.broker;

public class OperationNames {

    // Operation classes
    public static final String GAME_PREFIX = "gameImpl";
    public static final String CITY_PREFIX = "cityImpl";
    public static final String UNIT_PREFIX = "unitImpl";
    public static final String TILE_PREFIX = "tileImpl";


    // Game
    public static final String GAME_GET_AGE_OPERATION = "gameImpl_get-age";
    public static final String GAME_MOVE_UNIT_OPERATION = "gameImpl_move-unit";
    public static final String GAME_END_OF_TURN_OPERATION = "gameImpl_end-of-turn";
    public static final String GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_OPERATION = "gameImpl_change-workforce-focus-in-city";
    public static final String GAME_CHANGE_PRODUCTION_IN_CITY_AT_OPERATION = "gameImpl_change-production-in-city-at";
    public static final String GAME_PERFORM_UNIT_ACTION_AT_OPERATION = "gameImpl_perform-unit-action-at";
    public static final String GAME_ADD_OBSERVER_OPERATION = "gameImpl_add-observer";
    public static final String GAME_SET_TILE_FOCUS_OPERATION = "gameImpl_set-tile-focus";
    public static final String GAME_GET_TILE_AT_OPERATION = "gameImpl_get-tile-at";
    public static final String GAME_GET_CITY_AT_OPERATION = "gameImpl_get-city-at";
    public static final String GAME_GET_UNIT_AT_OPERATION = "gameImpl_get-unit-at";
    public static final String GAME_GET_PLAYER_IN_TURN_OPERATION = "gameImpl_get-player-in-turn";
    public static final String GAME_GET_WINNER_OPERATION = "gameImpl_get-winner";

    // City
    public static final String CITY_GET_OWNER_OPERATION = "cityImpl_get-city-at";
    public static final String CITY_GET_SIZE_OPERATION = "cityImpl_get-size";
    public static final String CITY_GET_TREASURY_OPERATION = "cityImpl_get-treasury";
    public static final String CITY_GET_PRODUCTION_OPERATION = "cityImpl_get-production";
    public static final String CITY_SET_PRODUCTION_OPERATION = "cityImpl_set-production";
    public static final String CITY_CHANGE_OWNER_OPERATION = "cityImpl_change-owner";

    // Unit
    public static final String UNIT_GET_TYPE_STRING_OPERATION = "unitImpl_get-type-string";
    public static final String UNIT_GET_OWNER_OPERATION = "unitImpl_get-owner";
    public static final String UNIT_GET_MOVE_COUNT_OPERATION = "unitImpl_get-move-count";
    public static final String UNIT_INCREMENT_MOVE_COUNT_OPERATION = "unitImpl_increment-move-count";
    public static final String UNIT_GET_DEFENSIVE_STRENGTH_OPERATION = "unitImpl_get-defensive-strength";
    public static final String UNIT_GET_ATTACKING_STRENGTH_OPERATION = "unitImpl_get-attacking-strength";
    public static final String UNIT_FORTIFY_OPERATION = "unitImpl_fortify";
    public static final String UNIT_IS_FORTIFIED_OPERATION = "unitImpl_is-fortified";
    public static final String UNIT_RESET_MOVE_COUNT_TO_ZERO_OPERATION = "unitImpl_reset-move-count-to-zero";

    public static final String CITY_GET_WORKFORCE_FOCUS_OPERATION = "cityImpl_get-workforce-focus";

    public static final String TILE_GET_TYPE_STRING = "tileImpl_get_type_string";

    public static final char SEPERATOR = '_';
}
