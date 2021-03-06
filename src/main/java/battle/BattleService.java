package battle;

import com.google.gson.Gson;
import entity.Card;
import entity.Deck;
import entity.SpellSet;
import holders.CardHolder;
import holders.SpellId;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import ourlists.OnTableList;

public class BattleService {

    @Autowired
    private CardHolder ch;
    @Autowired
    private SpellId spellId;

    public void setDeckCards(Battle b) {
        b.p1Deck = setDeck(b.p1.getCards());
        System.out.println("deck in battle:" + b.p1Deck.size());
        b.p2Deck = setDeck(b.p2.getCards());
        b.p1OnHand = set2cardsToHand(b.p1Deck);
        b.p2OnHand = set2cardsToHand(b.p2Deck);
    }

    private List<Card> setDeck(String deckPCard) {
        List<Card> l = new CopyOnWriteArrayList<>();
        Deck d = new Gson().fromJson(deckPCard, Deck.class);
        d.deck.forEach((Integer i) -> {
            Card c;
            try {
                c = (Card) ch.getCardById(i).clone();
                l.add(c);
            } catch (CloneNotSupportedException ex) {
                System.out.println("Clone error: " + ex);
            }
        });
        return l;
    }

    private List<Card> set2cardsToHand(List<Card> pDeck) {
        List<Card> l = new CopyOnWriteArrayList<>();
        for (Integer i = 0; i < 2; i++) {
            l.add(pDeck.remove(new Random().nextInt(pDeck.size())));
        }
        return l;
    }

    public Card add1CardToHand(List<Card> pDeck) {
        if (pDeck.size() > 0) {
            return pDeck.remove(new Random().nextInt(pDeck.size()));
        } else {
            return pDeck.remove(0);
        }
    }

    public int addPoint(int health, int damage, int points) {
        int point = 0;
        if (health > damage) {
            point = damage;
        } else {
            point = health;
        }
        int p = point + points;
        return p;

    }

    public void endTurn(Battle b, String login) {
        if (b.p1.getLogin().equals(login)) {
            p1EndTurn(b);
        }
        if (b.p2.getLogin().equals(login)) {
            p2EndTurn(b);
        }
    }

    public void beginNewTurn(Battle b) {
        b.p1MadeTurn = false;
        b.p2MadeTurn = false;
        b.turn++;
        if (b.turn < 10) {
            b.p1Mana = b.turn;
            b.p2Mana = b.turn;
        } else {
            b.p1Mana = 10;
            b.p2Mana = 10;
        }
        if (!b.p1Deck.isEmpty()) {
            b.p1OnHand.add(add1CardToHand(b.p1Deck));
            b.p2OnHand.add(add1CardToHand(b.p2Deck));
        }
        b.p1OnTable.forEach((c) -> {
            b.p1ActiveCards.put(c.getId(), c);
        });
        b.p2OnTable.forEach((c) -> {
            b.p2ActiveCards.put(c.getId(), c);
        });
    }

    private void p1EndTurn(Battle b) {
        b.p1ActiveCards.clear();
        b.p1AttackCard = null;
        b.p1MadeTurn = true;
        b.p1HeroPowerSelected = false;
        b.p1Attacked = false;
        b.p1ChosenHandCard = null;
    }

    private void p2EndTurn(Battle b) {
        b.p2ActiveCards.clear();
        b.p2AttackCard = null;
        b.p2MadeTurn = true;
        b.p2HeroPowerSelected = false;
        b.p2Attacked = false;
        b.p2ChosenHandCard = null;
    }

    public void p1CreatureTurn(Battle b, int id) {
        if (id < 0) {
            p1CreatureToCreatureAttack(b, id);
        } else if (id > 0) {
            p1CardChoice(b, id);
        } else if (id == 0 && !b.p2OnTable.checkTaunt() && b.p1AttackCard != null) {
            p1CreatureToHeroAttack(b);
        }
    }

    public void p2CreatureTurn(Battle b, int id) {
        if (id < 0) {
            p2CreatureToCreatureAttack(b, id);
        } else if (id > 0) {
            p2CardChoice(b, id);
        } else if (id == 0 && b.p2AttackCard != null && !b.p1OnTable.checkTaunt()) {
            p2CreatureToHeroAttack(b);
        }
    }

    private void p1CreatureToCreatureAttack(Battle b, int id) {
        if (!b.p2OnTable.checkTaunt()) {
            b.p2OnTable.forEach((c) -> {
                attackProcessP1Creature(b, c, id);
            });
        } else {
            b.p2OnTable.getTauntCards().forEach((c) -> {
                attackProcessP1Creature(b, c, id);
            });
        }
    }

    private void p2CreatureToCreatureAttack(Battle b, int id) {
        if (!b.p1OnTable.checkTaunt()) {
            b.p1OnTable.forEach((c) -> {
                attackProcessP2Creature(b, c, id);
            });
        } else {
            b.p1OnTable.getTauntCards().forEach((c) -> {
                attackProcessP2Creature(b, c, id);
            });
        }
    }

    private void attackProcessP1Creature(Battle b, Card c, int id) {
        if (c.getId() == -id && b.p1AttackCard != null) {
            if (!c.getShield() && !b.p1AttackCard.getShield()) {
                b.p1points = addPoint(c.getHealth(), b.p1AttackCard.getDamage(), b.p1points);
                b.p2points = addPoint(b.p1AttackCard.getHealth(), c.getDamage(), b.p2points);
                c.setHealth(c.getHealth() - b.p1AttackCard.getDamage());
                b.p1AttackCard.setHealth(b.p1AttackCard.getHealth() - c.getDamage());
                if (c.getHealth() <= 0) {
                    b.p2OnTable.remove(c);
                }
                if (b.p1AttackCard.getHealth() <= 0) {
                    b.p1OnTable.remove(b.p1AttackCard);
                }
                b.p1ActiveCards.remove(b.p1AttackCard.getId());
                b.p1AttackCard = null;
            } else if (c.getShield() && !b.p1AttackCard.getShield()) {
                c.setShield(false);
                b.p2points = addPoint(b.p1AttackCard.getHealth(), c.getDamage(), b.p2points);
                b.p1AttackCard.setHealth(b.p1AttackCard.getHealth() - c.getDamage());
                if (b.p1AttackCard.getHealth() <= 0) {
                    b.p1OnTable.remove(b.p1AttackCard);
                }
                b.p1ActiveCards.remove(b.p1AttackCard.getId());
                b.p1AttackCard = null;
            } else if (!c.getShield() && b.p1AttackCard.getShield()) {
                b.p1AttackCard.setShield(false);
                b.p1points = addPoint(c.getHealth(), b.p1AttackCard.getDamage(), b.p1points);
                c.setHealth(c.getHealth() - b.p1AttackCard.getDamage());
                if (c.getHealth() <= 0) {
                    b.p2OnTable.remove(c);
                }
                b.p1ActiveCards.remove(b.p1AttackCard.getId());
                b.p1AttackCard = null;
            } else {
                b.p1AttackCard.setShield(false);
                c.setShield(false);
            }
        }
    }

    private void attackProcessP2Creature(Battle b, Card c, int id) {
        if (c.getId() == -id && b.p2AttackCard != null) {
            if (!c.getShield() && !b.p2AttackCard.getShield()) {
                b.p2points = addPoint(c.getHealth(), b.p2AttackCard.getDamage(), b.p2points);
                b.p1points = addPoint(b.p2AttackCard.getHealth(), c.getDamage(), b.p1points);
                c.setHealth(c.getHealth() - b.p2AttackCard.getDamage());
                b.p2AttackCard.setHealth(b.p2AttackCard.getHealth() - c.getDamage());
                if (c.getHealth() <= 0) {
                    b.p1OnTable.remove(c);
                }
                if (b.p2AttackCard.getHealth() <= 0) {
                    b.p2OnTable.remove(b.p2AttackCard);
                }
                b.p2ActiveCards.remove(b.p2AttackCard.getId());
                b.p2AttackCard = null;
            } else if (c.getShield() && !b.p2AttackCard.getShield()) {
                c.setShield(false);
                b.p1points = addPoint(b.p2AttackCard.getHealth(), c.getDamage(), b.p1points);
                b.p2AttackCard.setHealth(b.p2AttackCard.getHealth() - c.getDamage());
                if (b.p2AttackCard.getHealth() <= 0) {
                    b.p2OnTable.remove(b.p2AttackCard);
                }
                b.p2ActiveCards.remove(b.p2AttackCard.getId());
                b.p2AttackCard = null;
            } else if (!c.getShield() && b.p2AttackCard.getShield()) {
                b.p2AttackCard.setShield(false);
                b.p2points = addPoint(c.getHealth(), b.p2AttackCard.getDamage(), b.p2points);
                c.setHealth(c.getHealth() - b.p2AttackCard.getDamage());
                if (c.getHealth() <= 0) {
                    b.p1OnTable.remove(c);
                }
                b.p2ActiveCards.remove(b.p2AttackCard.getId());
                b.p2AttackCard = null;
            } else {
                b.p2AttackCard.setShield(false);
                c.setShield(false);
            }
        }
    }

    private void p1CardChoice(Battle b, int id) {
        Set<Integer> activeCardsKeySet = b.p1ActiveCards.keySet();
        for (int cardId : activeCardsKeySet) {
            if (cardId == id) {
                for (Card c : b.p1OnTable) {
                    if (c.getId() == id) {
                        if (c.equals(b.p1AttackCard)) {
                            b.p1AttackCard = null;
                        } else {
                            b.p1AttackCard = c;
                        }
                        break;
                    }
                }
            }
        }
        for (Card c : b.p1OnHand) {
            if (c.getId() == id) {
                if (c != b.p1ChosenHandCard) {
                    b.p1ChosenHandCard = c;
                    b.p1AttackCard = null;
                } else {
                    b.p1ChosenHandCard = null;
                }
                break;
            }
        }
    }

    private void p2CardChoice(Battle b, int id) {
        if (b.p2ActiveCards != null) {
            Set<Integer> activeCardsKeySet = b.p2ActiveCards.keySet();
            for (int cardId : activeCardsKeySet) {
                if (cardId == id) {
                    for (Card c : b.p2OnTable) {
                        if (c.getId() == id) {
                            if (c.equals(b.p2AttackCard)) {
                                b.p2AttackCard = null;
                            } else {
                                b.p2AttackCard = c;
                            }
                            break;
                        }
                    }
                }
            }
        }
        for (Card c : b.p2OnHand) {
            if (c.getId() == id) {
                if (c != b.p1ChosenHandCard) {
                    b.p2ChosenHandCard = c;
                    b.p2AttackCard = null;
                } else {
                    b.p2ChosenHandCard = null;
                }
                break;
            }
        }
    }

    private void p1CreatureToHeroAttack(Battle b) {
        b.p1points = addPoint(b.p2Health, b.p1AttackCard.getDamage(), b.p1points);
        b.p2Health -= b.p1AttackCard.getDamage();
        b.p1ActiveCards.remove(b.p1AttackCard.getId());
        b.p1AttackCard = null;
    }

    private void p2CreatureToHeroAttack(Battle b) {
        b.p2points = addPoint(b.p1Health, b.p2AttackCard.getDamage(), b.p2points);
        b.p1Health -= b.p2AttackCard.getDamage();
        b.p2ActiveCards.remove(b.p2AttackCard.getId());
        b.p2AttackCard = null;
    }

    public void p1HeroPowerAttack(Battle b, int id) {
        if (id == 0) {
            p1HeroAttackEnemyHero(b);
        }
        if (id > 0) {
            p1HeroAttackHisCard(b, id);
        }
        if (id < 0) {
            p1HeroAttackEnemyCard(b, id);
        }
    }

    public void p2HeroPowerAttack(Battle b, int id) {
        if (id == 0) {
            p2HeroAttackEnemyHero(b);
        }
        if (id > 0) {
            p2HeroAttackHisCard(b, id);
        }
        if (id < 0) {
            p2HeroAttackEnemyCard(b, id);
        }
    }

    private void p1HeroAttackEnemyHero(Battle b) {
        b.p2Health -= 1;
        b.p1Mana -= 2;
        b.p1points += 1;
        b.p1HeroPowerSelected = false;
        b.p1Attacked = true;
    }

    private void p2HeroAttackEnemyHero(Battle b) {
        b.p1Health -= 1;
        b.p2HeroPowerSelected = false;
        b.p2Mana -= 2;
        b.p2points += 1;
        b.p2Attacked = true;
    }

    private void p1HeroAttackHisCard(Battle b, int id) {
        b.p1OnTable.stream().filter((c) -> (c.getId() == id)).forEachOrdered((c) -> {
            if (!c.getShield()) {
                c.setHealth(c.getHealth() - 1);
                if (c.getHealth() <= 0) {
                    b.p1OnTable.remove(c);
                }
                b.p1Mana -= 2;
                b.p1points += 1;
                b.p1HeroPowerSelected = false;
                b.p1Attacked = true;
            } else {
                c.setShield(false);
                b.p1Mana -= 2;
                b.p1HeroPowerSelected = false;
                b.p1Attacked = true;
            }
        });
    }

    private void p2HeroAttackHisCard(Battle b, int id) {
        b.p2OnTable.stream().filter((c) -> (c.getId() == id)).forEachOrdered((c) -> {
            if (!c.getShield()) {
                c.setHealth(c.getHealth() - 1);
                if (c.getHealth() <= 0) {
                    b.p2OnTable.remove(c);
                }
                b.p2HeroPowerSelected = false;
                b.p2Mana -= 2;
                b.p2points += 1;
                b.p2Attacked = true;
            } else {
                c.setShield(false);
                b.p2HeroPowerSelected = false;
                b.p2Mana -= 2;
                b.p2Attacked = true;
            }
        });
    }

    private void p1HeroAttackEnemyCard(Battle b, int id) {
        b.p2OnTable.stream().filter((c) -> (c.getId() == -id)).forEachOrdered((c) -> {
            if (!c.getShield()) {
                c.setHealth(c.getHealth() - 1);
                if (c.getHealth() <= 0) {
                    b.p2OnTable.remove(c);
                }
                b.p1Mana -= 2;
                b.p1points += 1;
                b.p1HeroPowerSelected = false;
                b.p1Attacked = true;
            } else {
                c.setShield(false);
                b.p1Mana -= 2;
                b.p1HeroPowerSelected = false;
                b.p1Attacked = true;
            }
        });
    }

    private void p2HeroAttackEnemyCard(Battle b, int id) {
        b.p1OnTable.stream().filter((c) -> (c.getId() == -id)).forEachOrdered((c) -> {
            if (!c.getShield()) {
                c.setHealth(c.getHealth() - 1);
                if (c.getHealth() <= 0) {
                    b.p1OnTable.remove(c);
                }
                b.p2HeroPowerSelected = false;
                b.p2Mana -= 2;
                b.p2points += 1;
                b.p2Attacked = true;
            } else {
                c.setShield(false);
                b.p2HeroPowerSelected = false;
                b.p2Mana -= 2;
                b.p2Attacked = true;
            }
        });
    }

    public void p1PutCard(Card c, Battle b) {
        if (c.getLevel() <= b.p1Mana) {
            System.out.println(c.getName());
            b.p1Mana -= c.getLevel();
            b.p1OnTable.add(c);
            b.p1OnHand.remove(c);
            if (c.getCharge()) {
                b.p1ActiveCards.put(c.getId(), c);
            }
            b.p1ChosenHandCard = null;
        }
    }

    public void p2PutCard(Card c, Battle b) {
        if (c.getLevel() <= b.p2Mana) {
            System.out.println(c.getName());
            b.p2Mana -= c.getLevel();
            b.p2OnTable.add(c);
            b.p2OnHand.remove(c);
            if (c.getCharge()) {
                b.p2ActiveCards.put(c.getId(), c);
            }
            b.p2ChosenHandCard = null;
        }
    }

    public boolean doSpell(String spell, Battle b) {
        if (spell != null) {
            SpellSet spellSet = new Gson().fromJson(spell, SpellSet.class);
            spellSet.spellSet.keySet().forEach((i) -> {
                Integer amount = spellSet.spellSet.get(i);
                spellId.spellId.get(i).doSpell(amount, b);
            });
        }
        return true;
    }

    public void clearDefeatedCard(OnTableList onTablep1, OnTableList onTablep2) {
        onTablep1.stream().filter((c) -> (c.getHealth() < 1)).forEachOrdered((c) -> {
            onTablep1.remove(c);
        });
        onTablep2.stream().filter((c) -> (c.getHealth() < 1)).forEachOrdered((c) -> {
            onTablep2.remove(c);
        });
    }

}
