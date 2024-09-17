package com.acledabankplc.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracUserLoggingRepository extends JpaRepository<TracUserLogging,Long> {
}
