import java.util.Date;

class Hospede { // arrumar nome do hospede

    final int TAM_DATAS = 100;
    private int cod;
    private String name;
    private int phone;
    private Date [] entry_dates =  new Date[TAM_DATAS];
    private Date [] departure_dates =  new Date[TAM_DATAS];
    private int number_dates;

    public Hospede(String name, int phone){

        this.name = name;
        this.phone = phone;

    }
}
