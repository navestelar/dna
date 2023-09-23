import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DnaMelhorado {
    private String nomeArquivo = "fitaDNA.txt";
    private String nomeArquivoNovo = "fitaDNAnova.txt";
    private int totalFitas = 0;
    private int numFitasInvalidas = 0;
    private StringBuilder fitasInvalidas = new StringBuilder();

    public void transcription(String nomeArquivo, String nomeArquivoNovo) {
        if(nomeArquivo!=null){
            this.nomeArquivo = nomeArquivo;
        }

        if(nomeArquivoNovo!=null) {
            this.nomeArquivoNovo = nomeArquivoNovo;
        }
        
        read();
    }

    public void mostrarNaTela() {
        System.out.println("Total de fitas do arquivo original: "+totalFitas);
        System.out.println("Número de fitas válidas: " + (totalFitas-numFitasInvalidas));
        System.out.println("Número de fitas inválidas: " + numFitasInvalidas);
        System.out.println(fitasInvalidas);
    }
    
    private void read() {
        // System.out.println("Lendo arquivo...");
        List<String> fitas = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String fita;
            while ((fita = br.readLine()) != null) {
                fitas.add(fita);
                totalFitas++;
            }
            totalFitas--;
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // System.out.println("Leitura do arquivo realizada com sucesso!");
        transcrever(fitas);
    }

    private void transcrever(List<String> fitas) {
        // System.out.println("Transcrevendo...");
        List<String> fitasNovas = new ArrayList<>();
        int i = 0;
        for (String fita : fitas) {
            i++;
            fitasNovas.add(transcreverFita(fita, i));
        }
        // System.out.println("Transcrição realizada com sucesso!");
        writeFita(fitasNovas);
    } 

    private String transcreverFita(String fita, int posicao) {
        char citosina = 'C';
        char adenina = 'A';
        char guanina = 'G';
        char timina = 'T';
        String fitaNova="";
        
        char[] nucleotideos = fita.toCharArray(); 

        StringBuilder stringBuilder = new StringBuilder(fitaNova);
        
        for (char nucleotideo : nucleotideos) {
            if (nucleotideo==citosina) 
                stringBuilder.append(guanina);
            else if(nucleotideo==guanina)
                stringBuilder.append(citosina);
            else if(nucleotideo==adenina)
                stringBuilder.append(timina);
            else if(nucleotideo==timina)
                stringBuilder.append(adenina);
            else{
                numFitasInvalidas++;
                fitasInvalidas.append("Fita Inválida posição "+posicao+" -"+fita+"\n");
                return "****FITA INVALIDA - "+fita;
            }
        }

        fitaNova = stringBuilder.toString();
        return fitaNova;
    }

    private void writeFita(List<String> fitasNovas) {
        // System.out.println("Criando arquivo...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivoNovo))) {
            for (String fitaNova : fitasNovas) {
                writer.write(fitaNova);
                writer.newLine();
            }
            // System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
