package ru.anastasia.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.anastasia.spring.models.Practice;
import ru.anastasia.spring.models.Progress;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    @Query(value = "SELECT AVG(CAST(p.correct AS DECIMAL(2,1))/(p.correct+p.mistakes))*100 " +
            "FROM Progress p WHERE p.idpracticefk = :practice " +
            "GROUP BY p.idpracticefk", nativeQuery = true)
    double getResult(@Param("practice") int practice);

    void deleteAllByIdPracticeFK(Practice practice);

    // select AVG(p.correct/(p.correct+p.mistakes)*100)
    // from Progress p where p.idPracticeFK.idFolderFK.idFolder = :p2 group by p.idPracticeFK.idFolderFK.idFolder

}
