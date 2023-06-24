package me.gethertv.luckyblock.data;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RandomEffect {

    private TypeRewards typeRewards;
    private List<ItemStack> items;

    private List<EntityData> entityData;

    public RandomEffect(TypeRewards typeRewards, List<ItemStack> items, List<EntityData> entityData) {
        this.typeRewards = typeRewards;
        this.items = items;
        this.entityData = entityData;
    }

    public TypeRewards getTypeRewards() {
        return typeRewards;
    }

    public void setTypeRewards(TypeRewards typeRewards) {
        this.typeRewards = typeRewards;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }

    public List<EntityData> getEntityData() {
        return entityData;
    }

    public void setEntityData(List<EntityData> entityData) {
        this.entityData = entityData;
    }


}
