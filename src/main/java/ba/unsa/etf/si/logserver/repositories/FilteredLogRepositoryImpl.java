package ba.unsa.etf.si.logserver.repositories;

import ba.unsa.etf.si.logserver.filters.LogFilter;
import ba.unsa.etf.si.logserver.models.Log;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class FilteredLogRepositoryImpl implements FilteredLogRepository {

    private final EntityManager entityManager;

    @Override
    public List<Log> findAllByFilter(LogFilter logFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> criteriaQuery = criteriaBuilder.createQuery(Log.class);

        Root<Log> root = criteriaQuery.from(Log.class);
        List<Predicate> predicates = new ArrayList<>();

        if(logFilter.getUsername() != null && !logFilter.getUsername().isEmpty()) {
            String pattern = String.format("%s", logFilter.getUsername());
            predicates.add(criteriaBuilder.equal(root.get("username"), pattern));
        }

        if(logFilter.getTo() != null ) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("timestamp"), logFilter.getTo()));
        }

        if(logFilter.getFrom() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timestamp"), logFilter.getFrom()));
        }

        if(logFilter.getAction() != null && !logFilter.getAction().isEmpty()) {
            String pattern = String.format("%s", logFilter.getAction());
            predicates.add(criteriaBuilder.equal(root.get("action").get("name"), pattern));
        }

        if(logFilter.getObject() != null && !logFilter.getObject().isEmpty()) {
            String pattern = String.format("%s", logFilter.getObject());
            predicates.add(criteriaBuilder.equal(root.get("action").get("object"), pattern));
        }

        Expression<Object> expression = root.get("timestamp");
        criteriaQuery.orderBy(criteriaBuilder.desc(expression));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Log> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
