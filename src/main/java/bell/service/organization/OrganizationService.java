package bell.service.organization;

import bell.entity.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cервис
 * Интефейс для работы с классом {@link Organization}
 */
@SuppressWarnings("JavaDoc")
@Service
public interface OrganizationService {

    /**
     * Список всех организаций
     */
    List<Organization> list();

    /**
     * Одна организация
     * @param id
     * @return объект класса {@link Organization}
     */
    Organization getById(int id);

    /**
     * Изменение данных офиса
     * @param id
     * @param name
     * @param fullName
     * @param inn
     * @param kpp
     * @param phone
     * @param address
     * @param isActive
     * @return объект класс {@link Organization}*/
    Organization update(int id,
                String name,
                String fullName,
                int inn,
                int kpp,
                String address,
                int phone, boolean isActive);

    /**
     * Удаление организации
     * @param id */
    void delete(int id);

    /**
     * Добавление организации
     * @param organization */
    void save(Organization organization);
}
