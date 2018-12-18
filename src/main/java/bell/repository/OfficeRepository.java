package bell.repository;

import bell.entity.Office;
import bell.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для класса {@link Office}
 **/
@Repository
public interface OfficeRepository extends JpaRepository<Office,Integer> {
//    /**
//     * Список всех работников
//     * @param organizationId
//     * @param name
//     * @param phone
//     * @param active
//     * @return {@link List}
//     */
//    List<Office> findAllByOrganizationIdAndNameAndPhoneAndActive(Organization organizationId,
//                                                                 String name,
//                                                                 String phone,
//                                                                 boolean active);
}
