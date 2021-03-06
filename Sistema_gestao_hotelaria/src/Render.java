import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class Render {

    private BufferedReader bufferedReader;
    private FileReader fileReader;


    public Render(){}

    public void renderCheckOutEstadia(String name,
                                      int codClient,
                                      int codApartment,
                                      int tip,
                                      int vrDiaria,
                                      LocalDate dateEntry,
                                      LocalDate dateOut,
                                      int numberHopedes,
                                      float vrTotal,
                                      long diarias){

        String tipoQuarto = tipoQuarto(tip);

        System.out.println("\nRELATORIO CHECKOUT\n");
        System.out.println("Nome: " + name);
        System.out.println("Codigo do cliente: " + codClient);
        System.out.println("Quarto: " + codApartment);
        System.out.println("Tipo Apartamento: " + tipoQuarto);
        System.out.println("Valor diaria: " + vrDiaria);
        System.out.println("Data de entrada: " + dateEntry);
        System.out.println("Data de saida: " + dateOut);
        System.out.println("Numero de hospedes: " + numberHopedes);
        System.out.println("Diarias: " + diarias);
        System.out.println("Valor total: " + vrTotal + "\n\n");
    }

    public void consultaQuarto(int tip, int vrDiaria, int capacity, String file, String ocupacao){

        String tipoQuarto = tipoQuarto(tip);

        System.out.println("\n\nRELATORIO QUARTO\n");
        System.out.println("Tipo: " + tipoQuarto);
        System.out.println("Diaria: " + vrDiaria);
        System.out.println("Capacidade: " + capacity);
        System.out.println("Ocupação: " + ocupacao);
        listaHistorico(file);

    }

    public void consultaHospede(String name, int cod, String phone, String file){

        System.out.println("\n\nRELATORIO CLIENTE\n");
        System.out.println("Nome: " + name);
        System.out.println("Codigo: " + cod);
        System.out.println("Telefone: " + phone);
        listaHistorico(file);
    }

    public void listaHistorico(String file){

        String linha = "";
        String[] array;
        // ABRINDO ARQUIVO DE DATAS PARA LEITURA
        try {

            this.fileReader = new FileReader(file);
            this.bufferedReader = new BufferedReader(this.fileReader);
            linha = this.bufferedReader.readLine();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\nReservas e histórico\n");

        while(linha != null){

            array = linha.split(";");
            System.out.println("Data de entrada: " + array[0]);
            System.out.println("Data de saida: " + array[1]);
            System.out.println("\n");

            try {
                linha = this.bufferedReader.readLine();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        }
        try{
            fileReader.close();
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public String tipoQuarto(int tip){

        return switch (tip) {
            case 1 -> "Standard";
            case 2 -> "Apartamento Vista Bosque";
            case 3 -> "Apartamento Vista Vale";
            case 4 -> "Suíte";
            default -> "";
        };
    }

}
