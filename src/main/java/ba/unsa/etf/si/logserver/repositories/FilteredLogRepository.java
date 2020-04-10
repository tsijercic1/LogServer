package ba.unsa.etf.si.logserver.repositories;

import ba.unsa.etf.si.logserver.filters.LogFilter;
import ba.unsa.etf.si.logserver.models.Log;

import java.util.List;

public interface FilteredLogRepository {
    List<Log> findAllByFilter(LogFilter logFilter);
}
