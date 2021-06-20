package com.aditya.jukeboxapplication.service.impl;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.aditya.jukeboxapplication.model.MusicAlbum;
import com.aditya.jukeboxapplication.model.Musician;
import com.aditya.jukeboxapplication.repository.MusicAlbumRepository;
import com.aditya.jukeboxapplication.repository.MusicianRepository;
import com.aditya.jukeboxapplication.service.JukeBoxService;

@Service
public class JukeBoxServiceImpl implements JukeBoxService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JukeBoxServiceImpl.class);

	@Autowired
	private MusicAlbumRepository musicAlbumRepository;

	@Autowired
	private MusicianRepository musicianRepository;

	@Override
	public MusicAlbum addMusicAlbum(MusicAlbum musicAlbum) {

		validateMusicAlbum(musicAlbum);
		Set<Musician> musicians = musicAlbum.getMusicians();
		musicAlbum.setMusicians(new HashSet<Musician>());
		musicAlbum = musicAlbumRepository.save(musicAlbum);

		if (!CollectionUtils.isEmpty(musicians)) {
			musicAlbum.setMusicians(musicians.stream().map(m -> {
				if (m.getId() != null) {
					m = musicianRepository.findById(m.getId()).get();
				}
				return m;
			}).collect(Collectors.toSet()));
			musicAlbum.getMusicians().forEach(m -> validateMusician(m));
			musicAlbum = musicAlbumRepository.save(musicAlbum);
		}
		return musicAlbum;
	}

	@Override
	public MusicAlbum updateMusicAlbum(Long musicAlbumId, MusicAlbum musicAlbum) {

		validateMusicAlbum(musicAlbum);
		Set<Musician> musicians = musicAlbum.getMusicians();
		musicAlbum.setMusicians(new HashSet<Musician>());
		musicAlbum.setId(musicAlbumId);
		musicAlbum = musicAlbumRepository.save(musicAlbum);

		if (!CollectionUtils.isEmpty(musicians)) {
			musicAlbum.setMusicians(musicians.stream().map(m -> {
				if (m.getId() != null) {
					m = musicianRepository.getOne(m.getId());
				}
				return m;
			}).collect(Collectors.toSet()));
			musicAlbum.getMusicians().forEach(m -> validateMusician(m));
			musicAlbum = musicAlbumRepository.save(musicAlbum);
		}
		return musicAlbum;
	}

	@Override
	public List<MusicAlbum> getAllMusicAlbums() {

		return musicAlbumRepository.findAll().stream().sorted(Comparator.comparing(MusicAlbum::getReleaseDate))
				.collect(Collectors.toList());
	}

	@Override
	public List<MusicAlbum> getMusicAlbumsByMusician(Long musicianId) {

		return musicAlbumRepository.findAllByMusiciansOrderByPrice(new Musician(musicianId, null, null, null));
	}

	@Override
	public Musician addMusician(Musician musician) {

		validateMusician(musician);
		return musicianRepository.save(musician);
	}

	@Override
	public Musician updateMusician(Long musicianId, Musician musician) {

		validateMusician(musician);
		musician.setId(musicianId);
		return musicianRepository.save(musician);
	}

	@Override
	public List<Musician> getMusiciansByMusicAlbum(Long musicAlbumId) {

		return musicianRepository.findAllByMusicAlbums(new MusicAlbum(musicAlbumId, null, null, null, null, null, null))
				.stream().sorted(Comparator.comparing(Musician::getMusicianName)).collect(Collectors.toList());
	}

	private void validateMusicAlbum(MusicAlbum musicAlbum) {

		if (musicAlbum == null) {
			LOGGER.error("No valid album data was provided.");
			throw new IllegalArgumentException("No valid album data was provided.");
		}

		if (!StringUtils.hasText(musicAlbum.getAlbumName()) || musicAlbum.getAlbumName().length() < 5) {
			LOGGER.error("No valid album name was provided.");
			throw new IllegalArgumentException("No valid album name was provided.");
		}

		if (musicAlbum.getReleaseDate() == null) {
			LOGGER.error("No album date was provided.");
			throw new IllegalArgumentException("No album date was provided.");
		}

		if (musicAlbum.getPrice() == null || musicAlbum.getPrice() < 100 || musicAlbum.getPrice() > 1000) {
			LOGGER.error("No valid album price was provided.");
			throw new IllegalArgumentException("No valid album price was provided.");
		}
	}

	private void validateMusician(Musician musician) {

		if (musician == null) {
			LOGGER.error("No valid musician data was provided.");
			throw new IllegalArgumentException("No valid musician data was provided.");
		}

		if (!StringUtils.hasText(musician.getMusicianName()) || musician.getMusicianName().length() < 3) {
			LOGGER.error("No valid musician name was provided.");
			throw new IllegalArgumentException("No valid musician name was provided.");
		}
	}

}
