package by.gstu.edu.theatre.entities.categories;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmallHallConstructor implements HallConstructor {
    private static final String SOURCE = "seats.xml";
    private Document xmlDocument;

    public SmallHallConstructor() {
        try {
            xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.getClass().getClassLoader().getResourceAsStream(SOURCE));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Category> create() {
        NodeList categories = xmlDocument.getDocumentElement().getElementsByTagName("category");
        List<Category> categoryList = new ArrayList<>();
        for(int i = 0; i < categories.getLength(); i++) {
            categoryList.add(createCategory(categories.item(i)));
        }

        return categoryList;
    }

    private Category createCategory(Node categoryNode) {
        NamedNodeMap attribute = categoryNode.getAttributes();
        String name = attribute.getNamedItem("name").getNodeValue();
        Byn price = new Byn(attribute.getNamedItem("price").getNodeValue());
        List<Seat> seats = new ArrayList<>();
        NodeList seatsNodes = categoryNode.getChildNodes();
        int row = 0;
        for(int i = 0; i < seatsNodes.getLength(); i++) {
            if (seatsNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                row++;
                seats.addAll(createSeats(row, Integer.parseInt(seatsNodes.item(i).getAttributes().getNamedItem("seats").getNodeValue())));
            }
        }

        return new Category(name, seats, price);
    }

    private List<Seat> createSeats(int row, int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> new Seat(row, i + 1))
                .collect(Collectors.toList());
    }
}
