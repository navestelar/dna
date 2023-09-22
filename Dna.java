import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Dna {
    public void trancrever(String path) {
        String fitaDNA = "novaFita.txt";
        String sequenciaTotal = "";

		try ( FileReader fileReader = new FileReader(path)) {
    
          	Scanner fileScanner = new Scanner(fileReader);
			int qtdFita = 0;
          	char citosina = 'C';
          	char adenina = 'A';
          	char guanina = 'G';
            char timina = 'T';
          	boolean fitaValida = true;
          	int qtdFitaInvalida = 0;
            String sequenciaFitaInvalida = "";
            
            while(fitaValida) {
                while (fileScanner.hasNextLine()){
                    String fita = fileScanner.nextLine();
                    String novaFita = "";
                    System.out.println(qtdFita);
                    
                    for(int i=0;i<fita.length();i++){
                        char nucleotideo = fita.charAt(i);
                        
                        if(nucleotideo == citosina) {
                            novaFita+="G";
                        }else if(nucleotideo == adenina) {
                            novaFita+="T";
                        }else if(nucleotideo == guanina) {
                            novaFita+="C";
                        }else if(nucleotideo == timina) {
                            novaFita+="A";
                        }else {
                            fitaValida = false;
                            qtdFitaInvalida++;
                            novaFita = "****FITA INVALIDA - "+fita;
                            sequenciaFitaInvalida+="Fita inválida posição "+ (qtdFita+1) + "-" +fita+"\n";
                        }
                        
                    }
                	sequenciaTotal+=novaFita+"\n";
                  
                    if (fileScanner.hasNextLine()){
                        qtdFita++;
                        fitaValida = true;
                    } else {
                        fitaValida = false;
                    }
                }
            }
            fileScanner.close();
            int qtdFitaValida = qtdFita-qtdFitaInvalida;
            System.out.println("Total de fitas do arquivo original: "+qtdFita);
            System.out.println("Número de fitas válidas: "+qtdFitaValida);
            System.out.println("Número de fitas inválidas: "+ qtdFitaInvalida);
            System.out.println("Listar as Fitas inválidas: \n"+sequenciaFitaInvalida);
              
	    } catch (IOException e) {  
            System.out.println("Erro ao ler arquivo" + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(fitaDNA)) {
            System.out.println("Gravando arquivo");
            writer.write(sequenciaTotal);
        } catch (IOException e) {  
            System.out.println("Erro ao gravar arquivo" + e.getMessage());
        }
    } 
}
