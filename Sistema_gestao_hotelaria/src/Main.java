import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;


public class Main {

    final static int NUM_HOSP = 100;
    final static int NUM_APART = 10;

    private static Hospede[] hospedes = new Hospede[NUM_HOSP];
    private static int numHospedes = 0;

    private static Apartment[] apartments = new Apartment[NUM_APART];
    private static int numApartments = 0;

    private static SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
    private static ParsePosition position = new ParsePosition(0);

//-----------------------------------------------------------MAIN----------------------------------------------------//
    public static void main(String[] args) throws ParseException {

        criaApartamentos();

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
                    checkOut();
                break;

                default:
                    System.out.println("Adeus");
                    sair = true;
                break;
            }
        }
    }

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

        hospedes[numHospedes] = new Hospede(name,numberPhone,cod);
        numHospedes++;
    }

    public static void checkIn(){

        int codClient, codApartament, numberHospedes, previsaoSaida, i=0;

        System.out.println("Codigo do Cliente: ");
        codClient = Integer.parseInt(Console.readLine());

        System.out.println("Codigo do Apartamento: ");
        codApartament = Integer.parseInt(Console.readLine());

        System.out.println("Data de entrada (format _/_/_): ");
        String auxDateEntry = Console.readLine();
        Date dateEntry = simpleDate.parse(auxDateEntry,position);
        position.setIndex(0);
        System.out.println(dateEntry);


        System.out.println("Data de saida (format _/_/_): ");
        String auxDateOut = Console.readLine();
        Date dateOut = simpleDate.parse(auxDateOut, position);

        System.out.println("Numero de hospedes: ");
        numberHospedes = Integer.parseInt(Console.readLine());

        // Falta validar o periodo minimo de 1 dia
        validarCheckIn(codClient,codApartament,dateEntry,dateOut,numberHospedes);
    }

    public static boolean validarCheckIn(int codClient, int codApartament, Date dateEntry, Date dateOut, int numberHospedes){
        boolean validade = false;

        if(apartments[codApartament].validaData(dateEntry,dateOut) == true && apartments[codApartament].validaHospedes(numberHospedes) == true){
            if(codApartament <= 10) {
                System.out.println("CheckIn confirmado!");
                validade = true;
            }else{
                System.out.println("Falha no CheckIn, confira os dados e tente novamente");
                validade = false;
            }
        }else{
            System.out.println("Validação negada");
            validade = false;
        }
        return validade;
    }

    public static void criaApartamentos(){

        for(int i = 0; i < NUM_APART; i++){

            apartments[i] = new Apartment();

        }
    }

    public static void checkOut(){

      // DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        int codApartmet;

        System.out.println("Codigo do Apartameto: ");
        codApartmet = Integer.parseInt(Console.readLine());

        //Converte Date->String->DateTime

        String dateEntry = apartments[codApartmet].getDate(1);
        String dateOut = apartments[codApartmet].getDate(0);



        DateTime auxDateEntry = new DateTime();
        DateTime auxDateOut = new DateTime();

       // Days dias = Days.daysBetween(data1,data2);
    }
}
