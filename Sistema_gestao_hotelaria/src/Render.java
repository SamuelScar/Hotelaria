import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Render {

    public Render(){};

    public void renderCheckOutEstadia(String name,
                                      int codClient,
                                      int codApartment,
                                      int tip,
                                      int vrDiaria,
                                      LocalDate dateEntry,
                                      LocalDate dateOut,
                                      int numberHopedes,
                                      int vrTotal,
                                      long diarias){

        String tipoQuarto = "";


        switch (tip){

            case 1:
                tipoQuarto = "Standard";
            break;
            case 2:
                tipoQuarto = "Apartamento Vista Bosque";
            break;
            case 3:
                tipoQuarto = "Apartamento Vista Vale";
            break;
            case 4:
                tipoQuarto = "Suíte";
            break;


        }

        System.out.println("RELATORIO CHECKOUT\n\n");
        System.out.println("Nome: " + name);
        System.out.println("\nCodigo do cliente: " + codClient);
        System.out.println("\nQuarto: " + codApartment);
        System.out.println("\nTipo Apartamento: " + tipoQuarto);
        System.out.println("\nValor diaria: " + vrDiaria);
        System.out.println("\nData de entrada: " + dateEntry);
        System.out.println("\nData de saida: " + dateOut);
        System.out.println("\nNumero de hospedes: " + numberHopedes);
        System.out.println("\nDiarias: " + diarias);
        System.out.println("\nValor total: " + vrTotal);
    }

}
