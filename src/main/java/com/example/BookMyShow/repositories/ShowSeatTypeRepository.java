package com.example.BookMyShow.repositories;

import com.example.BookMyShow.models.SeatType;
import com.example.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {
    @Override
    Optional<ShowSeatType> findById(Long aLong);
}
