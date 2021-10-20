import java.util.Date;

public class Apartment {

    final int TAM_DATAS = 100;
    private int cod;
    private int tip;
    private int capacity;
    private int occupation;
    Date [] entry_dates =  new Date[TAM_DATAS];
    Date [] departure_dates =  new Date[TAM_DATAS];

    public Apartment(){
        //
    }
}
