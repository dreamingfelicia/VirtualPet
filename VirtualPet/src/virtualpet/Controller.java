package virtualpet;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Timer;
/**
 *
 * @author feliciat
 */
public class Controller {
    private final Board board;
    private final PetModel pet;
    private int randomChoice;
    private final Timer timer;            // for tick event
    private final int DELAY = 3000; // 3 seconds
   public Controller (){
        pet = new PetModel();
        board = new Board(this);
        board.setVisible(true);
        timer = new Timer(DELAY, (ActionEvent e) -> { tick();});
        timer.start();
    }
    public void onFeed() {
        board.setChoiceButton("Choose food", true, "/ok.png");
        pet.feed(2);
        board.setHungerLabel("Hungriness: " + pet.getHungriness() + "%");
        displayImage();
    }
    public void onPlay() {
        board.setChoiceButton("Choose toy", true, "/ok.png");
        pet.play(1);
        board.setHappinessLabel("Happiness: " + pet.getHappiness() + "%");
        displayImage();
    }
    public void clean() {
        board.setChoiceButton("", false, "");
        int clean = pet.getCleanliness();
        clean = clean + 5;
        pet.setCleanliness(clean);
        if(pet.getCleanliness() < 50)
            board.setCleanLabel("Need to be cleaned");
        else
            board.setCleanLabel("You are clean and shine");
        displayImage();
    }
    public void choice(){
        Random rnd = ThreadLocalRandom.current();
        randomChoice = rnd.nextInt(3);
        String label = board.getChoiceButtonlabel();
        if (label.contains("food")){
            if (randomChoice == 0 )
                board.setChoiceButton("You get a bad food", true, "/sad.png");
            else if(randomChoice == 1){
                board.setChoiceButton("You get a good food", true, "/ok.png");
                randomChoice = randomChoice + 3;
            }
            else{
                board.setChoiceButton("You get a best food", true, "/happy.png");
                randomChoice = randomChoice + 6;
            }
           pet. feed(randomChoice);
        }
        if (label.contains("toy")){
            if (randomChoice == 0)
                board.setChoiceButton("You get a bad toy", true, "/sad.png");
            else if(randomChoice == 1 ){
                board.setChoiceButton("You get a good toy", true, "/ok.png");
                randomChoice = randomChoice + 2;
            }
            else{
                board.setChoiceButton("You get a best toy", true, "/happy.png");
                randomChoice = randomChoice + 4;
            }
            pet.play(randomChoice);
        }
        displayImage();
    }
        
    private void tick() {
        pet.play(-5);
        board.setHappinessLabel("Happiness: " + pet.getHappiness() + "%");
        
        pet.feed(-2);
        board.setHungerLabel("Hungriness: " + pet.getHungriness() + "%");
        int clean = pet.getCleanliness();
        pet.setCleanliness(clean - 1);
        if(pet.isClean())
            board.setCleanLabel("You are clean and shine");
        else
            board.setCleanLabel("Need to be cleaned");
        displayImage();
    }
    
    private void displayImage(){
        if(pet.isSad()){
            board.DisplaySadImage(true);
            board.DisplayHappyImage(false);
            board.DisplayImage(false);
        }
        else if(pet.isHappy()){
            board.DisplaySadImage(false);
            board.DisplayHappyImage(true);
            board.DisplayImage(false);
        }
        else{ 
            board.DisplaySadImage(false);
            board.DisplayHappyImage(false);
            board.DisplayImage(true); 
        }
    }
            
}