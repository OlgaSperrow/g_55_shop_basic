package client;

import app.controller.CustomerController;
import app.controller.ProductController;
import app.domain.Customer;
import app.domain.Product;
import app.repository.CustomerRepository;
import app.repository.CustomerRepositoryMap;
import app.repository.ProductRepository;
import app.repository.ProductRepositoryList;
import app.service.CustomerService;
import app.service.CustomerServiceImpl;
import app.service.ProductService;
import app.service.ProductServiceImpl;

import java.util.Scanner;

// Это имитация клиентской части.
// Представим, что наш Магазин - это бэк-енд, и он полностью
// лежит в пакете app.
// А это клиентская часть, представим, что это например фронт-енд
// или какое-то оконное приложение, запущенное на компьютере пользователя.
// Это приложение умеет обращаться на наш бэк-енд.
// Этот процесс мы будем имитировать при помощи вызова методов контроллера.
public class Client {

    private static Scanner scanner;
    private static ProductController productController;
    private static CustomerController customerController;

    public static void main(String[] args) {

        // Создаём объект репозитория
        ProductRepository productRepository = new ProductRepositoryList();

        // Создаём объект сервиса и в конструктор передаём ему объект репозитория,
        // чтобы сервис мог к нему обращаться
        ProductService productService = new ProductServiceImpl(productRepository);

        // Создаём объект контроллера и в конструктор передаём ему объект сервиса,
        // чтобы контроллер мог к нему обращаться
        productController = new ProductController(productService);

        CustomerRepository customerRepository = new CustomerRepositoryMap();
        CustomerService customerService = new CustomerServiceImpl(customerRepository, productService);
        customerController = new CustomerController(customerService);


        scanner = new Scanner(System.in);

        while (true) {
            try {

                System.out.println("Выберите действие:");
                System.out.println("1. Операции с продуктами");
                System.out.println("2. Операции с покупателями");
                System.out.println("0. Выход");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        productOperations();
                        break;
                    case 2:
                        customerOperations();
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод!");
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
                System.out.println("Выберите действие с продуктами:");
                System.out.println("1. Сохранение продукта");
                System.out.println("2. Получение всех продуктов");
                System.out.println("3. Получение одного продукта");
                System.out.println("4. Изменение одного продукта");
                System.out.println("5. Удаление продукта по идентификатору");
                System.out.println("6. Удаление продукта по наименованию");
                System.out.println("7. Восстановление продукта по идентификатору");
                System.out.println("8. Получение количества продуктов");
                System.out.println("9. Получение общей стоимости продуктов");
                System.out.println("10. Получение средней стоимости продукта");
                System.out.println("0. Выход");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Введите наименование продукта:");
                        String title = scanner.nextLine();
                        System.out.println("Введите цену продукта:");
                        double price = Double.parseDouble(scanner.nextLine());
                        // Вызов метода у контроллера - это имитация процесса, который происходит
                        // в реальных приложениях - отправка http-запроса через интернет
                        // от клиента на наше приложение
                        Product savedProduct = productController.save(title, price);
                        System.out.println("Сохранённый продукт:");
                        System.out.println(savedProduct);
                        break;
                    case 2:
                        productController.getAll().forEach(x -> System.out.println(x));
                        break;
                    case 3:
                        System.out.println("Введите идентифик2атор продукта:");
                        Long id = Long.parseLong(scanner.nextLine());
                        Product foundProduct = productController.getById(id);
                        System.out.println("Найденный продукт:");
                        System.out.println(foundProduct);
                        break;
                    case 4:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите новую цену продукта:");
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        productController.update(id, newPrice);
                        break;
                    case 5:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        productController.deleteById(id);
                        break;
                    case 6:
                        System.out.println("Введите наименование продукта:");
                        title = scanner.nextLine();
                        productController.deleteByTitle(title);
                        break;
                    case 7:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        productController.restoreById(id);
                        break;
                    case 8:
                        System.out.println("Количество продуктов - " + productController.getProductsCount());
                        break;
                    case 9:
                        System.out.println("Общая стоимость продуктов - " + productController.getProductsTotalCost());
                        break;
                    case 10:
                        System.out.println("Средняя цена продукта - " + productController.getProductsAveragePrice());
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void customerOperations() {
        while (true) {
            try {
                System.out.println("Выберите действие с покупателями:");
                System.out.println("1. Сохранить покупателя");
                System.out.println("2. Получить всех покупателей");
                System.out.println("3. Получить покупателя по ID");
                System.out.println("4. Обновить покупателя");
                System.out.println("5. Удалить покупателя по ID");
                System.out.println("6. Удалить покупателя по имени");
                System.out.println("7. Восстановить покупателя по ID");
                System.out.println("8. Получить количество покупателей");
                System.out.println("9. Получить стоимость корзины");
                System.out.println("10. Получить среднюю цену продуктов в корзине");
                System.out.println("11. Добавить продукт в корзину");
                System.out.println("12. Удалить продукт из корзины");
                System.out.println("13. Очистить корзину");
                System.out.println("0. Выход");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Введите имя покупателя:");
                        String name = scanner.nextLine();
                        Customer customer = customerController.save(name);
                        System.out.println("Сохранённый покупатель: " + customer);
                        break;
                    case 2:
                        customerController.getAllCustomers().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Введите ID покупателя:");
                        Long id = Long.parseLong(scanner.nextLine());
                        System.out.println("Покупатель: " + customerController.getCustomerById(id));
                        break;
                    case 4:
                        System.out.println("Введите ID покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите новое имя покупателя:");
                       String newName = scanner.nextLine();
                        customerController.customerUpdate(id, newName);
                        break;
                    case 5:
                        System.out.println("Введите ID покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.deleteCustomerById(id);
                        break;
                    case 6:
                        System.out.println("Введите имя покупателя:");
                        name = scanner.nextLine();
                        customerController.deleteCustomerByName(name);
                        break;
                    case 7:
                        System.out.println("Введите ID покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.restoreCustomerById(id);
                        break;
                    case 8:
                        System.out.println("Количество покупателей: " + customerController.getCustomerCount());
                        break;
                    case 9:
                        System.out.println("Введите ID покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Стоимость корзины: " + customerController.getCustomersCartTotalCostById(id));
                        break;
                    case 10:
                        System.out.println("Введите ID покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Средняя цена продукта: " + customerController.getAveragePriceOfCustomersCart(id));
                        break;
                    case 11:
                        System.out.println("Введите ID покупателя:");
                        Long customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите ID продукта:");
                        Long productId = Long.parseLong(scanner.nextLine());
                        customerController.addProductToCustomersCart(customerId, productId);
                        break;
                    case 12:
                        System.out.println("Введите ID покупателя:");
                        customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите ID продукта:");
                        productId = Long.parseLong(scanner.nextLine());
                        customerController.removeProductFromCustomersCart(customerId, productId);
                        break;
                    case 13:
                        System.out.println("Введите ID покупателя:");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.clearCustomersCart(id);
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод!");
                        break;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
