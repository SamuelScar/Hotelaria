import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Check {

    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private final DateTimeFormatter dateFormatEnv =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter dateFormatRecive = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Check(){}


    public int quartosOcupados(int quartos){
        int i,ocupados = 0;
        String ocupacao;

        for (i = 0; i < quartos; i++){

            ocupacao = ocupacaoQuarto("data"+i+".txt");

            if(ocupacao == "ocupado"){
                ocupados++;
            }
        }

        return ocupados;
    }

    public String ocupacaoQuarto(String file){

        LocalDate agora = LocalDate.now();
        String linha = "", ocupacao = "";
        boolean valida = false;

        // ABRINDO ARQUIVO DE DATAS PARA LEITURA
        try {

            this.fileReader = new FileReader(file);
            this.bufferedReader = new BufferedReader(this.fileReader);
            linha = this.bufferedReader.readLine();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        while(linha != null){

            LocalDate auxDateEntry = recebeDataArquivo(linha , 0);
            LocalDate auxDateOut = recebeDataArquivo(linha , 1);

            if(agora.isEqual(auxDateEntry) || agora.isEqual(auxDateOut)){
                valida = true;
            }else if(agora.isAfter(auxDateEntry) && agora.isBefore(auxDateOut)){
                valida = true;
            }

        }

        try{
            fileReader.close();
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        if(valida){
            ocupacao = "ocupado";
        }else{
            ocupacao = "desocupado";
        }

        return ocupacao;
    }


    public LocalDate data(String fileDates,int codCliente, int flag){

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

            int cliente = recebeCliente(linha , codCliente);

            if(cliente == codCliente){

                LocalDate auxDate = recebeDataArquivo(linha , flag);
                return auxDate;

            }else {

                try {
                    linha = bufferedReader.readLine();
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

            }
        }

        try{
            fileReader.close();
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        return dataAtual;
    }

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

    public void in(LocalDate dateEntry, LocalDate dateOut, int capacidadeQuarto, int quantidadeClientes, String fileApartment,String fileHospede,int codCliente){

        boolean validade = true;

        if( !(this.validaData(dateEntry, dateOut , fileApartment)) ){
            throw new RuntimeException("Data não disponível para este apartamento, por favor escolha outra");

        }else if( !(validaPeriodoMinimo(dateEntry, dateOut)) ){
            throw new RuntimeException("O periodo minimo deve ser de pelo omenos um dia no hotel");

        }else if( !(validaCapacidadeQuarto(capacidadeQuarto , quantidadeClientes)) ){
            throw new RuntimeException("A quantidade de clientes não é compativel com a capacidade do quarto");
        }
        if( validade ){

                registraData(dateEntry,dateOut,fileApartment,codCliente);
                registraData(dateEntry,dateOut,fileHospede,codCliente);

        }else{
            throw new RuntimeException("Houve uma falha no registro da data");
        }

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
//-----------------------------------------------------------------AMBOS-----------------------------------------------//
    private LocalDate recebeDataArquivo(String line ,int flag){  // FLAG 0 = Data de Entrada | FLAG 1 = Data de Saida

        String array[];
        array = line.split(";");
        LocalDate data = LocalDate.parse(array[flag], this.dateFormatRecive);

        return data;
    }

    public int recebeCliente(String line, int codCliente){

        String array[];
        array = line.split(";");
        int cliente =  Integer.parseInt(array[2]);

        return cliente;
    }

}
