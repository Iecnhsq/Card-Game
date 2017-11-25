<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Class Page</title>
        <style type="text/css" media="all">
            body {
                background-image: url(Image/fon.png);
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }
            div {
                width: 1024px;
                margin: auto;
                border-radius: 8px;
                background-color: rgba(84,82,71,.5);
                color: white;
                font: 14pt Arial, Helvetica, sans-serif;
                padding: 0px 0px 30px 0px;
                text-align: center;
            }
            p {
                text-align: justify;
                padding: 10px;
            }
            .leftimg {
                float:left;
                margin: 7px 7px 7px 0;
            }
            a {
                text-decoration: none;
                color: white;
                padding: 10px;
                background-color: #545247;
                border-radius: 4px;
            }
            a:hover {
                color: gainsboro;
            }
        </style>
    </head>
    <body>
        <div>
            <c:if test="${DK}">
                <h1 style="color: #c41e3b">Death Knight</h1>
                <p>
                    <img src="Image/icon-deathknight.png" class="leftimg"/>
                    Death Knights engage their foes up-close, supplementing swings of 
                    their weapons with dark magic that renders enemies vulnerable or damages 
                    them with unholy power. They drag foes into one-on-one conflicts, 
                    compelling them to focus their attacks away from weaker companions. 
                    To prevent their enemies from fleeing their grasp, death knights must 
                    remain mindful of the power they call forth from runes, and pace their attacks appropriately.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #c41e3b; border-radius: 15px"/>
                </p>
                <p style="text-align: center">
                    <img src="Image/normal(2).png" title="Will be available at level 4"/>
                    <img src="Image/normal(2).png" title="Will be available at level 8"/>
                    <img src="Image/rare(1).png" title="Will be available at level 15"/>
                    <img src="Image/rare(1).png" title="Will be available at level 25"/>
                    <img src="Image/epic(1).png" title="Will be available at level 40"/>
                    <img src="Image/legend(1).png" title="Will be available at level 50"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${DH}">
                <h1 style="color: #a330c9">Demon Hunter</h1>
                <p>
                    <img src="Image/icon-demonhunter.png" class="leftimg"/><br>
                    Forgoing heavy armor, Demon Hunters capitalize on speed, 
                    closing the distance quickly to strike enemies with one-handed 
                    weapons. However, Illidari must also use their agility defensively 
                    to ensure that battles end favorably.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #a330c9; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${DR}">
                <h1 style="color: #ff7c0a">Druid</h1>
                <p>
                    <img src="Image/icon-druid.png" class="leftimg"/>
                    Druids harness the vast powers of nature to preserve 
                    balance and protect life. With experience, druids can unleash nature’s 
                    raw energy against their enemies, raining celestial fury on them from a 
                    great distance, binding them with enchanted vines, or ensnaring them in unrelenting cyclones.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #ff7c0a; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if> 
            <c:if test="${HU}">
                <h1 style="color: #aad372">Hunter</h1>
                <p>
                    <img src="Image/icon-hunter.png" class="leftimg"/>
                    From an early age the call of the wild draws some 
                    adventurers from the comfort of their homes into the unforgiving 
                    primal world outside. Those who endure become hunters. Masters of 
                    their environment, they are able to slip like ghosts through the trees 
                    and lay traps in the paths of their enemies.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #aad372; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if> 
            <c:if test="${MA}">
                <h1 style="color: #68ccef">Mage</h1>
                <p>
                    <img src="Image/icon-mage.png" class="leftimg"/>
                    Students gifted with a keen intellect and unwavering discipline 
                    may walk the path of the mage. The arcane magic available to magi 
                    is both great and dangerous, and thus is revealed only to the most 
                    devoted practitioners. To avoid interference with their spellcasting, 
                    magi wear only cloth armor, but arcane shields and enchantments give 
                    them additional protection. To keep enemies at bay, magi can summon bursts 
                    of fire to incinerate distant targets and cause entire areas to erupt, setting groups of foes ablaze.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #68ccef; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if> 
            <c:if test="${MO}">
                <h1 style="color: #008467">Monk</h1>
                <p>
                    <img src="Image/icon-monk.png" class="leftimg"/>
                    When the pandaren were subjugated by the mogu centuries ago, 
                    it was the monks that brought hope to a seemingly dim future. 
                    Restricted from using weapons by their slave masters, these pandaren 
                    instead focused on harnessing their chi and learning weaponless combat.
                    When the opportunity for revolution struck, they were well-trained to throw off 
                    the yoke of oppression.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #008467; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${PA}">
                <h1 style="color: #f48cba">Paladin</h1>
                <p>
                    <img src="Image/icon-paladin.png" class="leftimg"/>
                    This is the call of the paladin: to protect the weak, to bring justice 
                    to the unjust, and to vanquish evil from the darkest corners of the world. 
                    These holy warriors are equipped with plate armor so they can confront the 
                    toughest of foes, and the blessing of the Light allows them to heal wounds and, 
                    in some cases, even restore life to the dead.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #f48cba; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${PR}">
                <h1 style="color: #fff">Priest</h1>
                <p>
                    <img src="Image/icon-priest.png" class="leftimg"/>
                    Priests are devoted to the spiritual, and express their 
                    unwavering faith by serving the people. For millennia they have 
                    left behind the confines of their temples and the comfort of their shrines 
                    so they can support their allies in war-torn lands. In the midst of terrible 
                    conflict, no hero questions the value of the priestly orders.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #fff; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${RO}">
                <h1 style="color: #fff468">Rogue</h1>
                <p>
                    <img src="Image/icon-rogue.png" class="leftimg"/>
                    For rogues, the only code is the contract, and their honor is 
                    purchased in gold. Free from the constraints of a conscience, these mercenaries 
                    rely on brutal and efficient tactics. Lethal assassins and masters of stealth, they 
                    will approach their marks from behind, piercing a vital organ and vanishing into the 
                    shadows before the victim hits the ground.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #fff468; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${SH}">
                <h1 style="color: #2359ff">Shaman</h1>
                <p>
                    <img src="Image/icon-shaman.png" class="leftimg"/>
                    Shaman are spiritual guides and practitioners, not of the divine, 
                    but of the very elements. Unlike some other mystics, shaman commune 
                    with forces that are not strictly benevolent. The elements are chaotic, 
                    and left to their own devices, they rage against one another in unending primal fury. 
                    It is the call of the shaman to bring balance to this chaos. Acting as moderators among 
                    earth, fire, water, and air, shaman summon totems that focus the elements to 
                    support the shaman’s allies or punish those who threaten them.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #2359ff; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${WL}">
                <h1 style="color: #9382c9">Warlock</h1>
                <p>
                    <img src="Image/icon-warlock.png" class="leftimg"/>
                    In the face of demonic power, most heroes see death. Warlocks see 
                    only opportunity. Dominance is their aim, and they have found a path to 
                    it in the dark arts. These voracious spellcasters summon demonic minions to 
                    fight beside them. At first, they command only the service of imps, but as a warlock’s 
                    knowledge grows, seductive succubi, loyal voidwalkers, and horrific felhunters 
                    join the dark sorcerer’s ranks to wreak havoc on anyone who stands in their master’s way.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #9382c9; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
            <c:if test="${WR}">
                <h1 style="color: #c69b6d">Warrior</h1>
                <p>
                    <img src="Image/icon-warrior.png" class="leftimg"/>
                    For as long as war has raged, heroes from every race have aimed to 
                    master the art of battle. Warriors combine strength, leadership, and a vast 
                    knowledge of arms and armor to wreak havoc in glorious combat. Some protect from 
                    the front lines with shields, locking down enemies while allies support the warrior from 
                    behind with spell and bow. Others forgo the shield and unleash their rage at the closest 
                    threat with a variety of deadly weapons.
                </p>
                <p style="text-align: center">
                    <img src="Image/cardback.png" style="border: 2px solid #c69b6d; border-radius: 15px"/>
                </p>
                <a href="/CardGame/card.html">Back to select a deck</a>
            </c:if>
        </div>
    </body>
</html>
