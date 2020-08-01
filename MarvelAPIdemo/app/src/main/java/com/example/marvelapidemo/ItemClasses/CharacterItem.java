package com.example.marvelapidemo.ItemClasses;

public class CharacterItem {
    private String characterImage;
    private String characterName;
    private String characterDescription;

    public CharacterItem(String characterImage, String characterName, String characterDescription) {
        this.characterImage = characterImage;
        this.characterName = characterName;
        this.characterDescription = characterDescription;
    }

    public String getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }
}
