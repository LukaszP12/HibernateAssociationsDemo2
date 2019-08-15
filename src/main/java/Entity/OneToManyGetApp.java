package Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class OneToManyGetApp {

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

        for (Property property : company.getProperties()){
            System.out.println(property);
        }

        factory.close();
    }

}
