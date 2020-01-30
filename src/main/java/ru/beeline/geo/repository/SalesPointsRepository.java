package ru.beeline.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beeline.geo.model.SalesPointDo;

import java.util.List;

public interface SalesPointsRepository extends JpaRepository<SalesPointDo, Integer> {

    List<SalesPointDo> getSalesPointDosByCity(String city);
}
