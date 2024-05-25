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
@RequestMapping("/initial/api")
//@RequestMapping("/api")

public class EmtApi {

    @GetMapping("/data")
    public List<ParadaEmtEntity> scrapeWebsite(@RequestParam("id") String id) {
        
        String url = "http://www.emtvalencia.es/QR.php?sec=est&p=" + id;

        // Hacer la solicitud GET a la URL
        RestTemplate restTemplate = new RestTemplate();
        String html = restTemplate.getForObject(url, String.class);

        // Parsear el HTML y extraer los divs deseados
        Document doc = Jsoup.parse(html);
        Elements divs = doc.select("div[style*=padding-left]");
        List<ParadaEmtEntity> result = new ArrayList<>();
        String numParada = null; 
        String nomParada = null; 
        String numLinea = null; 
        for (int i = 0; i < divs.size(); i++) {
            Element div = divs.get(i);
            // Extraer el texto dentro del div
            String text = div.text();

            // Separar la cadena en propiedad-valor
            String[] parts = text.split(" - ");
            if (parts.length < 2) {
                // Si la entrada no tiene el formato esperado, la ignoramos
                continue;
            }

            if (i == 0) {
                // Primer elemento: número de parada y nombre de la parada
                numParada = parts[0];
                nomParada = parts[1];
                
                // Si hay más partes, unirlas para obtener el nombre completo de la parada
                if (parts.length > 2) {
                    for (int j = 2; j < parts.length; j++) {
                        nomParada += " - " + parts[j];
                    }
                }
                continue; // Pasar al siguiente elemento sin procesar el primer elemento
            }

            // Resto de elementos: solo nombre de la parada
            String nomLinea = parts[0]; 
            String tiempo = parts.length > 1 ? parts[1] : null; 

            // Buscar todas las imágenes dentro del div actual
            Elements images = div.select("img[title]");
            for (Element image : images) {
                String title = image.attr("title"); // Obtener el título de la imagen
                if (!title.isEmpty()) {
                    numLinea = title; // Usar el título como número de línea si es un número
                }
            }

            if (!"Descarga APP".equals(nomLinea)) { // Filtrar la línea "Descarga APP"
                result.add(new ParadaEmtEntity(numParada, nomParada, nomLinea, tiempo, numLinea)); // Agregar objeto
                                                                                                   // ParadaEmtEntity al
                                                                                                   // resultado
            }
        }
        return result;
    }

}
