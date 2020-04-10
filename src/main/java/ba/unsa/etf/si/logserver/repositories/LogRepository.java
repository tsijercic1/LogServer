package ba.unsa.etf.si.logserver.repositories;

import ba.unsa.etf.si.logserver.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log,Long>, FilteredLogRepository {
    List<Log> findAllByAction_Name(String name);
}
