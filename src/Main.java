
public class Main {

    final static int NUM_HOSP = 100;
    private static Hospede[] hospede = new Hospede[NUM_HOSP];
    private static Render render = new Render();

    public static void main(String[] args){

        int escolha = 1;

        System.out.println("1 - Cadastrar Cliente");

        switch (escolha){
            case 1:
                cadastrarHospede();
            break;
        }
    }

    public static void cadastrarHospede(){

        render.renderCadastrarHospede();
        System.out.println("Codigo:");
        System.out.println("Nome:");
        System.out.println("Numero de contato:");

    }
}
