package io.github.gamblerDungeon;

public class CardData {
	private enum type {
		Attack, Skill
	};
	private String name;
	private String description;

	public CardData() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
