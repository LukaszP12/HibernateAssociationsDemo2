package Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class OneToManyDeleteApp {

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
/*        Company company = (Company) query.getSingleResult();

        System.out.println(company);

        for (Property property : company.getProperties()){
                if("Gdansk".equals(property.getCity())){
                    session.delete(property);
                }
        } */
        int idToDelete = 2;

        Property property = session.get(Property.class,idToDelete);

        session.delete(property);

        session.getTransaction().commit();

        factory.close();
    }


}
