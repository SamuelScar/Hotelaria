import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Apartment {

    final int TAM_DATAS = 100;
    private int cod;
    private String tip;
    private int capacity;
    private boolean occupation;
    Date [] entryDates =  new Date[TAM_DATAS];
    Date [] outDates =  new Date[TAM_DATAS];
    private int numDates = 0;
    private ParsePosition position = new ParsePosition(0);
    public Apartment(){

    }

    public boolean validaData(Date dateEntry,Date dateOut) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean validade = false;

        // Formata data de entrada
        String auxDate = dateFormat.format(dateEntry);
        dateEntry = dateFormat.parse(auxDate, position);

        // Formata data de saida
        auxDate = dateFormat.format(dateOut);
        dateOut = dateFormat.parse(auxDate, position);

        // Pega hora atual
        Date dataAtual = new Date(System.currentTimeMillis());
        auxDate = dateFormat.format(dataAtual);
        dataAtual = dateFormat.parse(auxDate, position);


        for (int i = 0; i < numDates; i++) {

            if (!(dateEntry.before(dataAtual) || dateOut.before(dataAtual))) {
                if (dateEntry.before(entryDates[i])) {
                    if (i != 0) {
                        if (dateEntry.after(outDates[i - 1]) && dateOut.before(entryDates[i])) { // se reserva esta no meio

                            this.armazenaData(dateEntry, dateOut);
                            validade = true;
                            return validade;

                        } else {
                            validade = false;
                            return validade;
                        }
                    } else if (dateOut.before(entryDates[i])) { //Data de entrada antes da primeira data

                        this.armazenaData(dateEntry, dateOut);
                        validade = true;
                        return validade;

                    } else {
                        validade = false;
                        return validade;
                    }
                } else if (dateEntry.after(outDates[i])) {

                    this.armazenaData(dateEntry, dateOut);
                    validade = true;
                    return validade;

                }
            } else {
                validade = false;
                return validade;  // se a data de entrada ou saida ja passou da data atual
            }
        }
        return validade;
    }

    private void armazenaData(Date dateEntry,Date dateOut){

        entryDates[numDates] = dateEntry;
        outDates[numDates] = dateOut;
        Arrays.sort(entryDates);
        Arrays.sort(outDates);

    }
}
