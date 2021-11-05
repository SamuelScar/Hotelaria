

public class Apartment {

    final int TAM_DATAS = 100;
    private int vrDiaria;
    private int cod;
    private int tip;
    private int capacity;
    private boolean occupation;
    Date [] entryDates =  new Date[TAM_DATAS];
    Date [] outDates =  new Date[TAM_DATAS];
    private int numDates = 0;
    public ParsePosition position = new ParsePosition(0);
    private Random aleatorio = new Random();

    public Apartment(){

        this.tip = aleatorio.nextInt(4) + 1;
        this.occupation = false;

        switch (this.tip){
            case 1:
                this.capacity = 2;
                this.vrDiaria = 268;
            break;

            case 2:
                this.capacity = 4;
                this.vrDiaria = 315;
            break;

            case 3:
                this.capacity = 4;
                this.vrDiaria = 353;
            break;

            case 4:
                this.capacity = 2;
                this.vrDiaria = 498;
            break;
        }
    }

    public boolean validaData(Date dateEntry,Date dateOut) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean validade = true;

        // Formata data de entrada
        String auxDate = dateFormat.format(dateEntry);
        dateEntry = dateFormat.parse(auxDate, position);

        // Formata data de saida
        position.setIndex(0);
        auxDate = dateFormat.format(dateOut);
        dateOut = dateFormat.parse(auxDate, position);

        // Pega hora atual
        Date dataAtual = new Date(System.currentTimeMillis());
        auxDate = dateFormat.format(dataAtual);
        dataAtual = dateFormat.parse(auxDate, position);


        if(dateEntry.after(dateOut)){
            validade = true;
            return validade;
        }

        // Verifica a possibilidade da data
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

        } // fim do laço
        return validade;
    }

    private void armazenaData(Date dateEntry,Date dateOut){

        entryDates[numDates] = dateEntry;
        outDates[numDates] = dateOut;
        Arrays.sort(entryDates);
        Arrays.sort(outDates);

    }

    public boolean validaHospedes(int numHopedes){

        if(numHopedes <= this.capacity+1){
            return true;
        }else {
            return false;
        }
    }

    public String getDate(int tip) {
        String date ="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String auxDate;

        // Pega hora atual
        Date dataAtual = new Date(System.currentTimeMillis());
        auxDate = dateFormat.format(dataAtual);
        dataAtual = dateFormat.parse(auxDate, position);


        for (int i = 0; i < this.numDates; i++){

            if(dataAtual.before(entryDates[i]) && dataAtual.after(outDates[i]) && tip == 1){
                date = dateFormat.format(entryDates[i]); // Recebe converte para string data de entrada se o tipo for 1

            }else if(dataAtual.before(entryDates[i]) && dataAtual.after(outDates[i]) && tip == 0){
                date = dateFormat.format(outDates[i]); // Recebe e converte para string data de saida se o tipo for 0
            }
        }
        return date;
    }
}
