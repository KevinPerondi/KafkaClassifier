

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Filtrador {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String nomeArq = args[0];
        File f = new File(nomeArq);
        Scanner scan = new Scanner(f);

        File file = new File("filtrado2.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        Filtro filt = new Filtro();

        while (scan.hasNext()) {
            String texto = scan.nextLine();
            if (texto.contains("{\"text\":")) {
//		System.out.println(texto);
                texto = filt.filtro(texto);
                texto = texto.substring(9, texto.length()-2);
                texto = texto.concat("\n");
                writer.write(texto);
                writer.flush();
            } else {
                texto = texto.substring(6, texto.length()-1);
                texto = texto.concat("\n");
                writer.write(texto);
                writer.flush();
            }
        }

    }

}

class Filtro {

    public String cleaning(String data) {
        String pattern = "\\@(.*?)\\:";
        data = data.replaceAll(pattern, "");
        return data;
    }

    public String filtro(String data) {
        if (data.contains("\"\\")) {
            data = data.replace("\"\\", "");
        }	
        if (data.contains("RT")) {
            data = data.replace("RT", "");
        }
        if (data.contains("@")) {
            data = cleaning(data);
        }
        if (data.contains("\"  ")) {
            String pattern = "\"  ";
            data = data.replaceAll(pattern, "\"");
        }
        if (data.contains("\" ")) {
            String pattern = "\" ";
            data = data.replaceAll(pattern, "\"");
        }
        if(data.contains("  ")){
            data = data.replaceAll("  ", " ");
        }
        return data;
    }

}

