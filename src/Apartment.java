import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Apartment {

    final int TAM_DATAS = 100;
    private int cod;
    private String tip;
    private int capacity;
    private boolean occupation;
    Date [] entry_dates =  new Date[TAM_DATAS];
    Date [] departure_dates =  new Date[TAM_DATAS];
    ParsePosition position = new ParsePosition(0);

    public Apartment(){

    }

    public boolean validaData(Date dateEntry,Date dateOut) {

        boolean validade = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Formata data de entrada
        String auxDate = dateFormat.format(dateEntry);
        dateEntry = dateFormat.parse(auxDate,position);

        // Formata data de saida
        auxDate = dateFormat.format(dateOut);
        dateOut = dateFormat.parse(auxDate,position);

        //Criar metodos para validar a data prevista usando o array de datas
        if(dateEntry.before(dateOut)){

        }

        return validade;
    }
}
