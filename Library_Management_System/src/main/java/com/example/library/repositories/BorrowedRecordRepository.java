package com.example.library.repositories;

import com.example.library.models.BorrowedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedRecordRepository extends JpaRepository<BorrowedRecord,Long> {
    List<BorrowedRecord> findByUserIdAndReturnedFalse(Long UserId);

}
