package net.sf.anathema.library.interaction.model;

public class Hotkey {
  private Character character;

  public Hotkey(Character character) {
    this.character = character;
  }

  public String asString() {
    return String.valueOf(character);
  }

  public char asCharacter() {
    return character;
  }
}