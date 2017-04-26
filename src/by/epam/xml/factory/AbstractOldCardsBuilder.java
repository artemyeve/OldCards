package by.epam.xml.factory;

import by.epam.xml.entity.OldCard;

import java.util.HashSet;
import java.util.Set;


public abstract class AbstractOldCardsBuilder {
    protected Set<OldCard> cards;

    public AbstractOldCardsBuilder() {
        cards = new HashSet<OldCard>();
    }

    public AbstractOldCardsBuilder(Set<OldCard> cards) {
        this.cards = cards;
    }

    public Set<OldCard> getOldCards() {
        return cards;
    }

    abstract public void buildSetOldCards(String fileName);
}
