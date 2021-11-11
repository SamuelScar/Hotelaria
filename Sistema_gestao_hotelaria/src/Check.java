import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Check {

    //private static String BASEPATH = "dates/";
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private final String dateFormat = "dd-MM-yyyy";


    public Check(){}


    public float out(String file, int vrDiaria, int capacidade, int quantidadeHospedes){

        float dias = (float) tempoEstadia(file);
        dias *= vrDiaria;

        if (quantidadeHospedes > capacidade){
            dias *= 1.30;
        }

        return dias;
    }


    public int codCliente(String fileDates){

        int cliente = 0;
        String linha = "";
        LocalDate dataAtual = LocalDate.now();

        // ABRINDO ARQUIVO DE DATAS PARA LEITURA
        try {

            fileReader = new FileReader(fileDates);
            bufferedReader = new BufferedReader(fileReader);
            linha = bufferedReader.readLine();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        while(linha != null){

            LocalDate auxDateOut = recebeDataArquivo(linha , 1);

            if(auxDateOut.equals(dataAtual)){

                cliente = recebeCliente(linha);

            }

            try {
                linha = bufferedReader.readLine();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return cliente;
    }

    public int tempoEstadia(String fileDates){

        int estadia = 0, cliente;
        String linha = "";
        LocalDate dataAtual = LocalDate.now();

        // ABRINDO ARQUIVO DE DATAS PARA LEITURA
        try {

            fileReader = new FileReader(fileDates);
            bufferedReader = new BufferedReader(fileReader);
            linha = bufferedReader.readLine();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        while(linha != null){

            LocalDate auxDateOut = recebeDataArquivo(linha , 1);

            if(auxDateOut.equals(dataAtual)){

                LocalDate auxDateEntry = recebeDataArquivo(linha , 0);
                estadia = (int) contaDias(auxDateEntry, auxDateOut);
                // FAZER MELHORIA DE PERFORMANCE NA PESQUISA
            }

            try {
                linha = bufferedReader.readLine();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        try{
            fileReader.close();
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
        return estadia;
    }


    public int recebeCliente(String linha){

        String array[];
        array = linha.split(";");

        int cliente = Integer.parseInt(array[2]);
        return cliente;
    }

    public long contaDias(LocalDate dateEntry, LocalDate dateOut){

        long diferencaDias = ChronoUnit.DAYS.between(dateEntry, dateOut);

        return diferencaDias;

    }
//------------------------------------------------------------CHECK_IN--------------------------------------------------//

    public boolean in(LocalDate dateEntry, LocalDate dateOut, int capacidadeQuarto, int quantidadeClientes, String fileApartment,String fileHospede,int codCliente){

        boolean validade = true;

        if( !(this.validaData(dateEntry, dateOut , fileApartment)) ){
            validade = false;

        }else if( !(validaPeriodoMinimo(dateEntry, dateOut)) ){
            validade =  false;

        }else if( !(validaCapacidadeQuarto(capacidadeQuarto , quantidadeClientes)) ){
            validade = false;

        }
        if( validade ){

            try {

                registraData(dateEntry,dateOut,fileApartment,codCliente);
                registraData(dateEntry,dateOut,fileHospede,codCliente);
                respostaValidade(validade);

            }catch (Exception e){
                System.out.println("Erro: " + e.getMessage());
                validade = false;
            }

        }else{
            respostaValidade(validade);

        }
        return validade;

    }


    private boolean validaData(LocalDate dateEntry, LocalDate dateOut, String fileDates) {

        boolean validade = true;
        String linha = "";
        LocalDate dataAtual = LocalDate.now();

        if(dateEntry.isAfter(dateOut)){

            validade = false;
            return validade;

        }

        if(dateEntry.isBefore(dataAtual)){

            validade = false;
            return validade;

        }

        // ABRINDO ARQUIVO DE DATAS PARA LEITURA
        try {

            fileReader = new FileReader(fileDates);
            bufferedReader = new BufferedReader(fileReader);
            linha = bufferedReader.readLine();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        while (linha != null){

            LocalDate auxDateEntry = recebeDataArquivo(linha , 0);
            LocalDate auxDateOut = recebeDataArquivo(linha , 1);

            if( !(dateEntry.isBefore(auxDateEntry) && dateOut.isBefore(auxDateOut)) ){

                validade = false;
                return validade;

            }else if( !(dateEntry.isAfter(auxDateOut) && dateOut.isAfter(auxDateOut)) ){

                validade = false;
                return validade;

            }

            try {
                linha = bufferedReader.readLine();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        }

        try {
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return validade;
    }


    private boolean validaPeriodoMinimo(LocalDate dateEntry, LocalDate dateOut){

        boolean validade;
        long diferencaDias = ChronoUnit.DAYS.between(dateEntry, dateOut);

        validade = diferencaDias >= 1;

        return validade;
    }


    private boolean validaCapacidadeQuarto(int capacidade, int clientes) {

        return clientes <= capacidade + 1;

    }


    private void respostaValidade(boolean validade){

        if(validade){
            System.out.println("Check In executado com sucesso");
        }else{
            System.out.println("Falha no Check In");
        }

    }

    private void registraData(LocalDate dateEntry, LocalDate dateOut, String fileDates, int codCliente){

        String line = dateEntry +";"+ dateOut +";"+codCliente;

        try {

            this.fileWriter = new FileWriter(fileDates,true);
            this.fileWriter.write(line + "\n");
            this.fileWriter.close();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }

    private LocalDate recebeDataArquivo(String line ,int flag){  // FLAG 0 = Data de Entrada | FLAG 1 = Data de Saida

        String recebe,array[];

        array = line.split(";");

        LocalDate data = LocalDate.parse(array[flag], DateTimeFormatter.ofPattern(this.dateFormat));

        return data;
    }

}
