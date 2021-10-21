import java.util.Date;

public class Apartment {

    final int TAM_DATAS = 100;
    private int cod;
    private String tip;
    private int capacity;
    private boolean occupation;
    Date [] entry_dates =  new Date[TAM_DATAS];
    Date [] departure_dates =  new Date[TAM_DATAS];

    public Apartment(){

    }
}
