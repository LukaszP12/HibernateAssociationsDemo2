package Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class OneToManyHqlApp {

    public static void main(String[] args) {        // stworzyc obiekt Configuration
        Configuration conf = new Configuration().configure("hibernate.cfg.xml");
        // wczytanie adnotacji
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        String getCompany = "select c.name from Property p join p.company where p.city='Sevilla'";
        String getCompany2 = "select c.name from Property p join p.company c join c.companyDetail cd where p.city='Barcelona' and cd.residence='Switzerland' ";
        String getCompany3 = "select c.name from Company c where size(c.properties) > 4";

        session.beginTransaction();
        Query query = session.createQuery(getCompany3);

        List<String> resultList = query.getResultList();
        session.getTransaction().commit();

        for (String result : resultList){
            System.out.println(result);
        }


        factory.close();

    }

}
