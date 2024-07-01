package com.example.BookMyShow.repositories;


import com.example.BookMyShow.models.Show;
import com.example.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long> {
    @Override
    Optional<ShowSeatType> findById(Long aLong);
    List<ShowSeatType> findAllByShow(Long showId);
}
