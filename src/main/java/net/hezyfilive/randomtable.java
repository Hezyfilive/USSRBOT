package net.hezyfilive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class randomtable {

    private final List<RewardItem> itemList = new ArrayList<RewardItem>();

    private boolean isInitialized = false;

    private float totalWeight;


    public randomtable add(String move, float weight){
        RewardItem rewardItem = new RewardItem(move, weight);
        itemList.add(rewardItem);
        return this;
    }
    public randomtable add(RewardItem rewardItem){
        itemList.add(rewardItem);
        return this;
    }
    public randomtable add(List<RewardItem> rewardItems){
        itemList.addAll(rewardItems);
        return this;
    }
    public randomtable build(){
        if(!isInitialized){
            totalWeight = 0;
            for(RewardItem x : itemList){
                totalWeight += x.weight;
            }
            isInitialized = true;
        }
        return this;
    }

    public String getRandomItem() throws Exception {
        if(isInitialized){
            float diceRoll = new Random().nextFloat(totalWeight-0.0f)+0.0f;
            for (RewardItem item : itemList){
                if(item.weight >= diceRoll) return item.move;
                diceRoll -= item.weight;
            }
        }

        throw new Exception("Reward generation failed");
    }
}
