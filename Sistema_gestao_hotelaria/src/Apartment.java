import java.io.FileWriter;
import java.util.Random;

public class Apartment {

    //private String BASEPATH = "dates";
    private int vrDiaria;
    private int cod;
    private int tip;
    private int capacity;
    private int numberHospedes;
    private boolean occupation;
    private int numDates = 0;
    private String file;
    private Random aleatorio = new Random();

    public Apartment(int i) {

        this.tip = aleatorio.nextInt(4) + 1;
        this.occupation = false;
        this.file = "data" + i + ".txt";
        this.cod = i;
        try {

            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        switch (this.tip) {
            case 1:
                this.capacity = 2;
                this.vrDiaria = 268;
                break;

            case 2:
                this.capacity = 4;
                this.vrDiaria = 315;
                break;

            case 3:
                this.capacity = 4;
                this.vrDiaria = 353;
                break;

            case 4:
                this.capacity = 2;
                this.vrDiaria = 498;
                break;
        }
    }

//------------------------------------------------------GETTERS--------------------------------------------------------//
    public int getTip() { return tip; }

    public int getVrDiaria() { return vrDiaria; }

    public int getNumberHospedes() { return numberHospedes; }

    public int getCapacity() { return this.capacity; }

    public String getFile(){ return this.file; }


//------------------------------------------------------SETTERS--------------------------------------------------------//


    public void setNumberHospedes(int numberHospedes) {
        this.numberHospedes = numberHospedes;
    }


}
