package core.model.gameplay.items;

import core.model.gameplay.gameobjects.Unit;

import java.util.Map;

public class Weapon extends Item implements IBonusGiver {

    public Weapon(ItemInstanceKind instanceKind, String description, Map<String, Integer> values) {
        super(instanceKind, description, values);
        setItemOperation(ItemOperation.DRESS);
    }

    /**
     * Increases target's physical and magic attack
     * @param target is an unit, which attributes will change
     */
    @Override
    public void setBonuses(Unit target) {
        target.getAttribute().increasePAttack(getParameter("pAttack"));
        target.getAttribute().increaseMAttack(getParameter("mAttack"));
    }

    /**
     * Decreases target's physical and magic attack
     * @param target is an unit, which attributes will change
     */
    @Override
    public void unsetBonuses(Unit target) {
        target.getAttribute().decreasePAttack(getParameter("pAttack"));
        target.getAttribute().decreaseMAttack(getParameter("mAttack"));
    }

}