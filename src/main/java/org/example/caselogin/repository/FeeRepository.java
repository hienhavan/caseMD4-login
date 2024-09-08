package org.example.caselogin.repository;

import org.example.caselogin.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository

public interface FeeRepository extends JpaRepository<Fee, Integer> {

    @Query("SELECT f FROM Fee f WHERE f.dueDate BETWEEN :currentDate AND :threeDaysLater")
    List<Fee> findFeesDueSoon(@Param("currentDate") Date currentDate, @Param("threeDaysLater") Date threeDaysLater);
}
