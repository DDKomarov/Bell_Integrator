package bell.service.office;

import bell.entity.Office;
import bell.entity.Organization;
import bell.repository.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cервис
 * Интефейс для работы с классом {@link Office}
 */
@SuppressWarnings("JavaDoc")
@Service
public interface OfficeService {


    /**
     * Список всех офисов
     * @param active
     * @param name
     * @param organizationId
     * @param phone
     * @return {@link OfficeRepository#findAllByOrganizationIdAndNameAndPhoneAndActive(Organization, String, String, boolean)}
     */
    List<Office> list(Organization organizationId,
                      String name,
                      String phone,
                      boolean active);

    /**
     * Один офис
     * @param id
     * @return объект класса {@link Office}
     */
    Office getById(int id);

    /**
     * Изменение данных офиса
     * @param id
     * @param address
     * @param isActive
     * @param name
     * @param phone
     * @return объект класс {@link Office}*/
    Office update(int id, String name, String address, String phone, Boolean isActive);

    /**
     * Удаление офиса
     * @param id */
    void delete(int id);

    /**
     * Добавление офиса
     * @param office*/
    void save(Office office);
}
