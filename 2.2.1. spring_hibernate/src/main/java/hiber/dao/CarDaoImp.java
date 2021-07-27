package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public void getDriver(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from User user where user.car.model = :model and user.car.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);

        List<User> users = query.getResultList();
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println("Id = " + user.getId());
                System.out.println("First Name = " + user.getFirstName());
                System.out.println("Last Name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
                System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
                System.out.println();
            }
        } else System.out.println("No car = " + model + " series " + series);
    }
}



