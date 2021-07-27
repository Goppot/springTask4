package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW")));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Mercedes")));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("Audi")));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("Volvo")));
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("Lada")));
        userService.add(new User("User8", "Lastname8", "user8@mail.ru", new Car("UAZ")));
        userService.add(new User("User9", "Lastname9", "user9@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println();
        }

        carService.getDriver("BMW", 1);
        carService.getDriver("Mercedes", 2);
        carService.getDriver("Audi", 3);
        carService.getDriver("Volvo", 4);
        carService.getDriver("Lada", 5);
        carService.getDriver("UAZ", 6);
        carService.getDriver("Toyota", 10);
        context.close();
    }
}
