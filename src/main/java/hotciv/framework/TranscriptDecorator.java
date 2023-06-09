package hotciv.framework;

import hotciv.variants.thetaCiv.ThetaGameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TranscriptDecorator implements Game {

    private final Game game;
    private final List<String> transcript;
    private final Player playerInTurn = Player.RED;

    public TranscriptDecorator(Game gameToTranscript) {
        this.game = gameToTranscript;
        this.transcript = new ArrayList<>();
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        transcript.add(game.getUnitAt(from).getOwner() + " moves an " + game.getUnitAt(from).getTypeString() + " from " + from + " to " + to + ".");
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        transcript.add(playerInTurn.name() + " ends turn.");
        game.endOfTurn();
    }

    @Override
    public void changeWorkforceFocusInCityAt(Position p, String balance) {
        game.changeWorkforceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        transcript.add(getPlayerInTurn().name() + " changes production in city at " + p + " to " + unitType + ".");
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        String unitAction = null;
        if (Objects.equals(getUnitAt(p).getTypeString(), GameConstants.ARCHER)) unitAction = "fortify";
        else if (Objects.equals(getUnitAt(p).getTypeString(), GameConstants.SETTLER)) unitAction = "build city";
        else if (Objects.equals(getUnitAt(p).getTypeString(), ThetaGameConstants.SANDWORM)) unitAction = "devour";
        transcript.add(getPlayerInTurn() + " performs " + unitAction + " with unit at " + p + ".");
        game.performUnitActionAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    @Override
    public String getId() {
        return null;
    }

}
