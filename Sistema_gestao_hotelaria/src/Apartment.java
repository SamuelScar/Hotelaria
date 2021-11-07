import java.io.FileWriter;
import java.util.Random;

public class Apartment {

    private String BASEPATH = "dates";
    final int TAM_DATAS = 100;
    private int vrDiaria;
    private int cod;
    private int tip;
    private int capacity;
    private boolean occupation;
    private int numDates = 0;
    private String file;
    private Random aleatorio = new Random();

    public Apartment(int i){

        this.tip = aleatorio.nextInt(4) + 1;
        this.occupation = false;
        this.file = "data"+i+".txt";

        try{

            FileWriter fileWriter = new FileWriter(BASEPATH+this.file);
            fileWriter.close();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        switch (this.tip){
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


//
//    private void armazenaData(Date dateEntry,Date dateOut){
//
//        entryDates[numDates] = dateEntry;
//        outDates[numDates] = dateOut;
//        Arrays.sort(entryDates);
//        Arrays.sort(outDates);
//
//    }
//
//    public boolean validaHospedes(int numHopedes){
//
//        if(numHopedes <= this.capacity+1){
//            return true;
//        }else {
//            return false;
//        }
//    }
//
//    public String getDate(int tip) {
//        String date ="";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String auxDate;
//
//        // Pega hora atual
//        Date dataAtual = new Date(System.currentTimeMillis());
//        auxDate = dateFormat.format(dataAtual);
//        dataAtual = dateFormat.parse(auxDate, position);
//
//
//        for (int i = 0; i < this.numDates; i++){
//
//            if(dataAtual.before(entryDates[i]) && dataAtual.after(outDates[i]) && tip == 1){
//                date = dateFormat.format(entryDates[i]); // Recebe converte para string data de entrada se o tipo for 1
//
//            }else if(dataAtual.before(entryDates[i]) && dataAtual.after(outDates[i]) && tip == 0){
//                date = dateFormat.format(outDates[i]); // Recebe e converte para string data de saida se o tipo for 0
//            }
//        }
//        return date;
//    }


    public int getCapacity() {
        return this.capacity;
    }

    public String getFile(){
        return this.file;
    }
}
