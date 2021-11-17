import java.io.FileWriter;

class Hospede { // arrumar nome do hospede

    private int cod;
    private String name;
    private String phone;
    private String file;


    public Hospede(String name, String phone, int cod){

        this.cod = cod;
        this.name = name;
        this.phone = phone;

        // FAZER VERIFICAÇÃO SE CLIENTE JA NAO EXISTE
        this.file = "cliente"+cod+".txt";

        try{

            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.close();

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }

    }


    public String getFile() {
        return this.file;
    }
    public String getPhone() { return this.phone; }
    public int getCod() {
        return this.cod;
    }
    public String getName() { return this.name; }
}
