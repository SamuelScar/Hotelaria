import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    final static int NUM_HOSP = 100;
    private static Hospede[] hospedes = new Hospede[NUM_HOSP];
    private static int numHospedes = 0;
    private static Render render = new Render();

    public static void main(String[] args){

        int escolha = 1;

        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Check In");
        escolha = Integer.parseInt(Console.readLine());

        switch (escolha){
            case 1:
                cadastrarHospede();
            break;
        }
    }

    public static void cadastrarHospede(){

        int cod, numberPhone;
        String name;

        render.renderCadastrarHospede();
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

        int codClient, codApartament, numberHospedes, previsãoSaida;
        String auxDateEntry, auxDateDeparture;
        //Date dateEntry = new Date;

        System.out.println("Codigo do Cliente: ");
        codClient = Integer.parseInt(Console.readLine());

        System.out.println("Codigo do Apartamento: ");
        codApartament = Integer.parseInt(Console.readLine());

        System.out.println("Data de entrada (format _/_/_): ");
        auxDateEntry = Console.readLine();

        System.out.println("Data de saida (format _/_/_): ");
        auxDateDeparture = Console.readLine();

        validarCheckIn();
    }

    public static void validarCheckIn(){

        //fazer validação dos dados para executar o checkIn

    }
}
