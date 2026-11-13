/**
 * Cell immunity
 * After being cured, the cell gains a chance to gain immunity from an infected cell
 */
public class CellImmunity {
    private boolean hasImmunity = false;
    private int curedCount = 0;
    private float chanceOfImmunity = 0.70f;
    private int minImmunityStrength = 70;
    private int maxImmunityStrength = 90;
    private int currImmunityStrength = 0;
    private boolean canHaveImmunity = false;
    private long immunityTimer = 0;

    public boolean getImmunityAllowed() {
         return canHaveImmunity;
    }

    public void setIfImmunityAllowed(boolean value) {
        this.canHaveImmunity = value;
    }

    public int getCuredCount() {
        return this.curedCount;
    }

    public void incrementCuredCount() {
        this.curedCount++;
    }
    
    public boolean getImmunity() {
        return this.hasImmunity;
    }

    public void setImmunity(boolean immunity) {
        this.hasImmunity = immunity;
    }

    /**
     * Check if the cell is immune
     */
    public void updateImmunity() {

        if (this.curedCount >= 1 && !hasImmunity) {
            // calc immunity strength by getting a random number between 70 and 99
            currImmunityStrength = (int)Math.floor(Math.random() * (maxImmunityStrength - minImmunityStrength + 1) + minImmunityStrength);
            this.hasImmunity = true;
            this.curedCount = 0;
        }
    }

    /**
     * Calculate the decay rate of cell immunity
     * @param deltaTime
     */
    public void decay(long deltaTime) {
        if (hasImmunity) {
            immunityTimer += deltaTime;

            // Make a timer and decrement immmunity strength by 9 approximately every 500 miliseconds
            while (immunityTimer >= 500) {
                immunityTimer -= 500;

                if (currImmunityStrength > 0) {
                    currImmunityStrength -= 9;
                }
                
                if (currImmunityStrength <= 0) {
                    hasImmunity = false;
                    break;
                }
            }
        }
    }

    /**
     * Calculate cell immunity
     * @return
     */
    
    public boolean calculateIfImmune() {
        if (curedCount > 0) {
            return Math.random() < chanceOfImmunity;
        } else {
            return false;
        }
    }
}