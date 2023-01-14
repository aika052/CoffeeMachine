import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Кофемашина");
        System.out.println("Введите сумму денег: ");
        int moneyAmount = new Scanner(System.in).nextInt();
        ArrayList<Product> productsAL = new ArrayList<>();
        try {
        List<String> lines = Files.readAllLines(Paths.get("drinks.txt")); // При работе с файлами ОБЯЗАТЕЛЬНО нужно добавлять исключение!
        for (String line : lines) {
            String[] fragments = line.split("\s+"); // пропустить пробел и больше до числа
            if ((fragments.length < 2) || (fragments[0].isEmpty())) {   // если в строке меньше двух эл-ов, то не парсим
                System.out.println("Недостаточно данных о напитке");
                continue;
            }
                int priceForProduct = Integer.parseInt(fragments[1]);
                productsAL.add(new Product(fragments[0], priceForProduct));
            }
        }   catch (IOException e) { e.printStackTrace(); }

        chekPrices(productsAL, moneyAmount); // если у класса напрямую можно вызывать метод - этот метод static. Может быть вызван у класса без создания объекта
    }
    public static void chekPrices(ArrayList<Product> productsAL, int moneyAmount) {
        boolean canBuyAnything = false;
        for (Product product : productsAL) {
            if(moneyAmount >= product.getPrice()) {
                System.out.println("Вы можете купить " + product.getName());
                canBuyAnything=true;
            }
        }
    if(!canBuyAnything) System.out.println("Недостаточно средств :(");
    }

}
