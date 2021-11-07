import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CheckIn {

    private static String BASEPATH = "dates/";
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String dateFormat = "dd/MM/yyyy";


    public CheckIn(){}

    public boolean validaCheckIn(LocalDate dateEntry, LocalDate dateOut, int capacidadeQuarto, int quantidadeClientes, String fileDates,int codCliente){

        boolean validade = true;

        if( !(this.validaData(dateEntry, dateOut , fileDates)) ){
            validade = false;

        }else if( !(validaPeriodoMinimo(dateEntry, dateOut)) ){
            validade =  false;

        }else if( !(validaCapacidadeQuarto(capacidadeQuarto , quantidadeClientes)) ){
            validade = false;

        }
        if( validade == true ){

            try {

                registraData(dateEntry,dateOut,fileDates,codCliente);
                respostaValidade(validade);

            }catch (Exception e){
                validade = false;
                System.out.println("Erro: " + e.getMessage());
            }

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

        boolean validade = true;
        long diferencaDias = ChronoUnit.DAYS.between(dateEntry, dateOut);

        if(diferencaDias >= 1){
            validade = true;

        }else{
         validade = false;

        }

        return validade;
    }


    private boolean validaCapacidadeQuarto(int capacidade, int clientes) {

        if (clientes > capacidade + 1){
           return false;
        }else{
            return true;
        }

    }


    private void respostaValidade(boolean validade){

        if(validade == true){
            System.out.println("Check In executado com sucesso");
        }else{
            System.out.println("Falha no Check In");
        }

    }

    private void registraData(LocalDate dateEntry, LocalDate dateOut, String fileDates, int codCliente){

        String line = dateEntry +";"+ dateOut +";"+codCliente;

        try {

            fileWriter = new FileWriter(fileDates,true);
            fileWriter.write(line + "\n");
            fileWriter.close();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }

    //-----------------------------------------------FUNÇÕES_AUXILIARES-----------------------------------------------//
    private LocalDate recebeDataArquivo(String line ,int flag){

        LocalDate data;
        String array[] = new String[3];

        array = line.split(";");

        data = LocalDate.parse(array[flag] , DateTimeFormatter.ofPattern(this.dateFormat));

        return data;
    }

}
