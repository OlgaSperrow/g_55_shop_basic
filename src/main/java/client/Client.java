package client;

import app.controler.ProductController;
import app.domain.Product;
import app.repository.ProductRepository;
import app.repository.ProductRepositoryList;
import app.service.ProductService;
import app.service.ProductServiceImpl;

import java.util.Scanner;

public class Client {
    private static Scanner scanner;
    private static ProductController productController;

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryList();
        ProductService productService = new ProductServiceImpl(productRepository);
        productController = new ProductController(productService);

        scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Выберите действие: ");
                System.out.println("1. Операции с продуктами");
                System.out.println("2. Операции с покупателями");
                System.out.println("0. Выход");

                int choiсe = Integer.parseInt(scanner.nextLine());

                switch (choiсe) {
                    case 1:
                        productOperations();
                        break;
                    case 2:
                        customerOperations();
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод");
                        break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
    }

    public static void productOperations() {
        while (true) {
            try {
                System.out.println("Выберите действие с продуктами: ");
                System.out.println("1. Сохранение продукта ");
                System.out.println("2. Получение всех продуктов ");
                System.out.println("3. Получение одного продукта по его идентификатору");
                System.out.println("4. Изменение одного продукта ");
                System.out.println("5. Удаление продукта по его идентификатору ");
                System.out.println("6. Удаление продукта по его наименованию ");
                System.out.println("7. Восстановление продукта по идентификатору");
                System.out.println("8. Получение количества продуктов ");
                System.out.println("9. Получение общей стоимости продуктов ");
                System.out.println("10. Получение средней стоимости продуктов ");
                System.out.println("0. Выход");

                int choiсe = Integer.parseInt(scanner.nextLine());

                switch (choiсe) {
                    case 1:
                        System.out.println("Введите наименование продукта");
                        String title = scanner.nextLine();
                        System.out.println("Введите цену продукта");
                        double price = Double.parseDouble(scanner.nextLine());

                        Product savedProduct = productController.save(title, price);
                        System.out.println("Сохраненный продукт: ");
                        System.out.println(savedProduct);
                        break;
                    case 2:
                        productController.getAll().forEach(x -> System.out.println(x));
                        break;
                    case 3:
                        System.out.println("ВВедите идентификатор продукта ");
                        Long id = Long.parseLong(scanner.nextLine());
                        Product foundProduct = productController.getById(id);
                        System.out.println("Найденный продукт ");
                        System.out.println(foundProduct);
                        break;
                    case 4:
                        System.out.println("Введите идентификатор продукта ");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите новую цену продукта");
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        productController.update(id, newPrice);
                        break;
                    case 5:
                        System.out.println("Введите идентификатор продукта ");
                        id = Long.parseLong(scanner.nextLine());
                        productController.deleteById(id);
                        break;
                    case 6:
                        System.out.println("Введите наименование продукта");
                        title = scanner.nextLine();
                        productController.deleteByTitle(title);
                        break;
                    case 7:
                        System.out.println("Введите идентификатор продукта ");
                        id = Long.parseLong(scanner.nextLine());
                        productController.restoreById(id);
                        break;
                    case 8:
                        System.out.println("Количество продуктов: " + productController.getProductsCount());
                        break;
                    case 9:
                        System.out.println("Общая стоимость продуктов: " + productController.getProductsCount());
                        break;
                    case 10:
                        System.out.println("Средняя стоимость продукта: " + productController.getProductAveragePrice());
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод");
                        break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public static void customerOperations() {

    }
}
