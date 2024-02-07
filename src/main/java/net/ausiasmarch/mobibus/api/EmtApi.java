package net.ausiasmarch.mobibus.api;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.ausiasmarch.mobibus.entity.ParadaEmtEntity;
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmtApi {
    
    @GetMapping("/data")
    public List<ParadaEmtEntity> scrapeWebsite(@RequestParam("id") String id) {
        // Construir la URL con el ID de la parada
        String url = "http://www.emtvalencia.es/QR.php?sec=est&p=" + id;
    
        // Hacer la solicitud GET a la URL
        RestTemplate restTemplate = new RestTemplate();
        String html = restTemplate.getForObject(url, String.class);
    
        // Parsear el HTML y extraer los divs deseados
        Document doc = Jsoup.parse(html);
        Elements divs = doc.select("div[style*=padding-left]");
        List<ParadaEmtEntity> result = new ArrayList<>();
        String numParada = null; // Variable para almacenar el número de parada
        String nomParada = null; // Variable para almacenar el nombre de la parada
        for (int i = 0; i < divs.size(); i++) {
            Element div = divs.get(i);
            // Extraer el texto dentro del div
            String text = div.text();
            
            // Separar la cadena en propiedad-valor
            String[] parts = text.split(" - ");
            if (i == 0) {
                // Primer elemento: número de parada y nombre de la parada
                if (parts.length >= 2) {
                    numParada = parts[0]; // Asignar el número de parada
                    nomParada = parts[1]; // Asignar el nombre de la parada
                    // Si hay más partes, unirlas para obtener el nombre completo de la parada
                    if (parts.length > 2) {
                        for (int j = 2; j < parts.length; j++) {
                            nomParada += " - " + parts[j];
                        }
                    }
                    result.add(new ParadaEmtEntity(numParada, nomParada, null, null)); // Agregar objeto ParadaEmtEntity al resultado
                } else {
                    // Manejar el caso donde no hay suficientes elementos
                    // Puedes lanzar una excepción, agregar un objeto con valores predeterminados, etc.
                }
            } else {
                // Resto de elementos: solo nombre de la parada
                String nomLinea = parts[0]; // El primer elemento se considera como nombre de la línea
                String tiempo = parts.length > 1 ? parts[1] : null; // Si hay un segundo elemento, se considera como tiempo
                if (!"Descarga APP".equals(nomLinea)) { // Filtrar la línea "Descarga APP"
                    result.add(new ParadaEmtEntity(numParada, nomParada, nomLinea, tiempo)); // Agregar objeto ParadaEmtEntity al resultado
                }
            }
        }
        return result;
    }




    // @GetMapping("/data")
    // public String scrapeWebsite(@RequestParam("id") String id) {
    //     // Construir la URL con el ID de la parada
    //     String url = "http://www.emtvalencia.es/QR.php?sec=est&p=" + id;

    //     // Hacer la solicitud GET a la URL
    //     RestTemplate restTemplate = new RestTemplate();
    //     String html = restTemplate.getForObject(url, String.class);

    //     // Parsear el HTML y extraer los divs deseados
    //     Document doc = Jsoup.parse(html);
    //     Elements divs = doc.select("div[style*=padding-left]");
    //     StringBuilder result = new StringBuilder();
    //     for (Element div : divs) {
    //         result.append(div.outerHtml()).append("\n");
    //     }
    //     return result.toString();
    // }
}
