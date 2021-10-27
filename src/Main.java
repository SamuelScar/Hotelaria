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
    private static SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
    private static Apartment[] apartments = new Apartment[NUM_APART];
    private static int numApartments = 0;
    private static ParsePosition position = new ParsePosition(0);

    public static void main(String[] args) throws ParseException {

        int escolha = 1;

        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Check In");
        escolha = Integer.parseInt(Console.readLine());

        switch (escolha){
            case 1:
                cadastrarHospede();
            break;

            case 2:
                checkIn();
            break;
        }
    }

    public static void cadastrarHospede(){

        int cod, numberPhone;
        String name;
        System.out.println("CADASTRO DE HOSPEDE");
        System.out.println("Codigo:");
        cod = Integer.parseInt(Console.readLine());

        System.out.println("Nome:");
        name = Console.readLine();

        System.out.println("Numero de contato:");
        numberPhone = Integer.parseInt(Console.readLine());

        hospedes[numHospedes].cadastraHospede(name,numberPhone,cod);
        numHospedes++;
    }

    public static void checkIn(){

        int codClient, codApartament, numberHospedes, previsaoSaida, i=0;
        boolean validade = false;
        String auxDate;
        Date dateEntry;

        System.out.println("Codigo do Cliente: ");
        codClient = Integer.parseInt(Console.readLine());

        System.out.println("Codigo do Apartamento: ");
        codApartament = Integer.parseInt(Console.readLine());

        System.out.println("Data de entrada (format _/_/_): ");
        auxDate = Console.readLine();
        dateEntry = simpleDate.parse(auxDate,position);
        System.out.println(dateEntry);

        System.out.println("Data de saida (format _/_/_): ");
        auxDate = Console.readLine();
        Date dateOut = simpleDate.parse(auxDate, position);

        validarCheckIn(codClient,codApartament,dateEntry,dateOut);
    }

    public static void validarCheckIn(int codClient, int codApartament, Date dateEntry, Date dateOut){

        if(apartments[codApartament].validaData(dateEntry,dateOut)){
            System.out.println("Validação confirmada");
        }else{
            System.out.println("Validação negada");
        }
    }
}
