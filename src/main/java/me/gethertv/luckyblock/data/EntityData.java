package me.gethertv.luckyblock.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EntityData {

    private EntityType entity;
    private int amount;

    public EntityData(EntityType entity, int amount) {
        this.entity = entity;
        this.amount = amount;
    }

    public EntityType getEntity() {
        return entity;
    }

    public void setEntity(EntityType entity) {
        this.entity = entity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
