package virtualpet;

/**
 *
 * @author feliciat
 */
public class PetModel {
    private int happiness = 100;
    private int hungriness = 0;
    private int cleanliness = 100;

    public void setCleanliness(int clean) {
        if (clean > 100)
            clean = 100;
        if (clean <0) 
            clean = 0;
        this.cleanliness = clean;
    }
    public void setHappiness(int happy) {
        if (happy > 100)
            happy = 100;
        if (happy <0) 
            happy = 0;
        this.happiness = happy;
    }
    public void setHungriness(int hungry) {
        if (hungry > 100)
            hungry = 100;
        if (hungry <0) 
            hungry = 0;
        this.hungriness = hungry;
    }

    public int getCleanliness() {
        return cleanliness;
    }
    public int getHappiness() {
        return happiness;
    }
    public int getHungriness() {
        return hungriness;
    }
    public boolean isSad() {
        return hungriness > 50 || cleanliness < 40 || happiness < 50;
    }
    public boolean isHappy() {
        return hungriness < 40 && cleanliness > 50 && happiness > 60;
    }
    public boolean isClean() {
        return cleanliness > 50;
    }
    public void feed(int c) {
        int hunger = hungriness - c;
        setHungriness(hunger);
    }
    public void play(int c){
        int happy = happiness + c;
        setHappiness(happy);
    }
}
