package bell.repository;

import bell.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для класса {@link Organization}
 **/
@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

}
