package com.aditya.jukeboxapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.jukeboxapplication.model.MusicAlbum;
import com.aditya.jukeboxapplication.model.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

	List<Musician> findAllByMusicAlbums(MusicAlbum musicAlbum);

}
