import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Main {

    final static int NUM_HOSP = 100;
    final static int NUM_APART = 10;

    private static Hospede[] hospedes = new Hospede[NUM_HOSP];
    private static int numHospedes = 1;

    private static Apartment[] apartments = new Apartment[NUM_APART];
    private static int numApartments = 0;

    private static Render render = new Render();
    private static Check check = new Check();

    //private static String BASEPATH = "datas";
    private static String dateFormat = "dd/MM/yyyy";

//-----------------------------------------------------------MAIN----------------------------------------------------//


    public static void main(String[] args) {

        inicializar();

        boolean sair = false;

        while(sair == false) {
            int escolha = 1;

            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Check In");
            System.out.println("3 - Check Out");

            escolha = Integer.parseInt(Console.readLine());

            switch (escolha) {
                case 1:
                    cadastrarHospede();
                    break;

                case 2:
                    checkIn();
                break;

                case 3:
                    //checkOut();
                break;

                default:
                    System.out.println("Adeus");
                    removeArquivosDatas();
                    sair = true;
                break;
            }
        }
    }


//-----------------------------------------------FUNÇÕES_DE_INICIALIZAÇÃO----------------------------------------------//


    public static void inicializar(){

        removeArquivos();
        //inicializaDiretorio();
        criaApartamentos();
    }


    public static void removeArquivos(){

        removeArquivosDatas();
        removeArquivosClientes();

    }


    public static void removeArquivosClientes(){

        for(int i = 0; i < 100; i++){

            try {

                File file = new File("cliente"+i+".txt");

                if(file.isFile()){
                    file.delete();
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        }

    }


    public static void removeArquivosDatas(){

        for(int i = 0; i < 10; i++) {

            try {

                File file = new File("data"+i+".txt");
                file.delete();

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

    }


//    public static void inicializaDiretorio(){
//
//        new File(BASEPATH).mkdir();
//
//    }

    public static void criaApartamentos(){

        for(int i = 0; i < NUM_APART; i++){

            apartments[i] = new Apartment(i);

        }
    }



//-----------------------------------------------FUNÇÕES_PRINCIPAIS----------------------------------------------------//



    public static void cadastrarHospede(){

        int cod;
        String name, numberPhone;
        System.out.println("CADASTRO DE HOSPEDE");
        System.out.println("Codigo:");
        cod = Integer.parseInt(Console.readLine());

        System.out.println("Nome:");
        name = Console.readLine();

        System.out.println("Numero de contato:");
        numberPhone = Console.readLine();

        hospedes[cod] = new Hospede(name,numberPhone,cod);
        //numHospedes++;
    }

    public static void checkIn(){

        int codClient, codApartment, quantidadeClientes;
        String auxDate = "";

        System.out.println("Codigo do Cliente: ");
        codClient = Integer.parseInt(Console.readLine());

        System.out.println("Codigo do Apartamento: ");
        codApartment = Integer.parseInt(Console.readLine());

        System.out.println("Data de entrada (format _/_/_): ");
        auxDate = Console.readLine();
        LocalDate dateEntry = LocalDate.parse(auxDate , DateTimeFormatter.ofPattern(dateFormat));

        System.out.println("Data de saida (format _/_/_): ");
        auxDate = Console.readLine();
        LocalDate dateOut = LocalDate.parse(auxDate , DateTimeFormatter.ofPattern(dateFormat));

        System.out.println("O numero de vagas para este quarto é " + apartments[codApartment].getCapacity());
        System.out.println("Numero de hospedes: ");
        quantidadeClientes = Integer.parseInt(Console.readLine());

        if(check.in(dateEntry,
                dateOut,
                apartments[codApartment].getCapacity(),
                quantidadeClientes,
                apartments[codApartment].getFile(),
                hospedes[codClient].getFile(),codClient)){

            apartments[codApartment].setNumberHospedes(quantidadeClientes);

        }
    }

    public static void checkOut(){

        int codApartment, cliente;
        float estadia;
        System.out.println("Codigo do apartamento: ");
        codApartment = Integer.parseInt(Console.readLine());

        estadia = check.out(apartments[codApartment].getFile(),
                apartments[codApartment].getVrDiaria(),
                apartments[codApartment].getCapacity(),
                apartments[codApartment].getNumberHospedes());

        cliente = check.recebeCliente(apartments[codApartment].getFile());

//        long diarias = ChronoUnit.DAYS.between(dateEntry, dateOut);
//
//        render.renderCheckOutEstadia(hospedes[cliente].getName(),
//                hospedes[cliente].getCod(),codApartment,
//                apartments[codApartment].getTip(),
//                apartments[codApartment].getVrDiaria(),
//                dateEntry,
//                dateOut,
//                apartments[codApartment].getNumberHospedes(),
//                vrTotal,
//                diarias);

    }
}
