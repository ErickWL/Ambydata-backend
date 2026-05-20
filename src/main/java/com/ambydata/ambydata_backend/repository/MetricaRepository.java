package com.ambydata.ambydata_backend.repository;

import com.ambydata.ambydata_backend.model.Metrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricaRepository extends JpaRepository<Metrica, Long> {
}