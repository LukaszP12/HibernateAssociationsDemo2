package Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class OneToManySaveApp {


    public static void main(String[] args) {
        // stworzyc obiekt Configuration
        Configuration conf = new Configuration().configure("hibernate.cfg.xml");
        // wczytanie adnotacji
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        String getCompany = "select c from Company c where c.name='Strefakursow'";

        session.beginTransaction();

        Query query = session.createQuery(getCompany);
        Company company = (Company) query.getSingleResult();

        System.out.println(company);

        Property property1 = new Property("Warszawa", 40);
        Property property2 = new Property("Gdansk",30);

        company.addProperty(property1);
        company.addProperty(property2);

        session.persist(property1);
        session.persist(property2);

        session.getTransaction().commit();

        factory.close();
    }


}