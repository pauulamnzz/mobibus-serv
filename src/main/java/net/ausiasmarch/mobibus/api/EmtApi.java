package net.ausiasmarch.mobibus.api;

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
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmtApi {
    @GetMapping("/data")
    public String scrapeWebsite(@RequestParam("id") String id) {
        // Construir la URL con el ID de la parada
        String url = "http://www.emtvalencia.es/QR.php?sec=est&p=" + id;
    
        // Hacer la solicitud GET a la URL
        RestTemplate restTemplate = new RestTemplate();
        String html = restTemplate.getForObject(url, String.class);
    
        // Parsear el HTML y extraer los divs deseados
        Document doc = Jsoup.parse(html);
        Elements divs = doc.select("div[style*=padding-left]");
        StringBuilder result = new StringBuilder();
        for (Element div : divs) {
            // Extraer solo el texto dentro del div y agregarlo al resultado
            result.append(div.text()).append("\n");
        }
        return result.toString();
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
