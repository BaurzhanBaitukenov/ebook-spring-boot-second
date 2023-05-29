package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.dto.ClubDto;
import com.example.springbootebooksecond.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);
    ClubDto findClubById(long clubId);
    void updateClub(ClubDto clubDto);
    void delete(long clubId);
    List<ClubDto> searchClubs(String query);
}