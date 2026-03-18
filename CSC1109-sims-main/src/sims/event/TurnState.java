package sims.event;

public class TurnState {
    private int energyLeft;
    private final int maxEnergy;

    protected TurnState(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        this.energyLeft = maxEnergy;
    }

    protected int getEnergyLeft() {
        return energyLeft;
    }

    protected int getMaxEnergy() {
        return maxEnergy;
    }

    protected boolean hasEnoughEnergy(int amt){
        return amt <= energyLeft;
    }

    protected boolean spendEnergy(int amt) {
        if (amt > energyLeft) {
            return false;
        }
        energyLeft -= amt;
        return true;
    }

    public void gainEnergy(int amt){
        if (amt<= 0){
            return;
        }

        energyLeft += amt;
        if (energyLeft > maxEnergy){
            energyLeft = maxEnergy;
        }
    }


}
