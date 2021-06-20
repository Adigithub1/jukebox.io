package com.aditya.jukeboxapplication.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.jukeboxapplication.model.MusicAlbum;
import com.aditya.jukeboxapplication.model.Musician;
import com.aditya.jukeboxapplication.service.JukeBoxService;

@RestController
@RequestMapping(value = "/jukebox")
public class JukeBoxController {

	@Autowired
	private JukeBoxService jukeBoxService;

	@PostMapping("/albums")
	public MusicAlbum addMusicAlbum(@RequestBody MusicAlbum musicAlbum) {

		return jukeBoxService.addMusicAlbum(musicAlbum);
	}

	@PutMapping("/albums/{albumId}")
	public MusicAlbum updateMusicAlbum(@PathVariable("albumId") Long musicAlbumId, @RequestBody MusicAlbum musicAlbum) {

		return jukeBoxService.updateMusicAlbum(musicAlbumId, musicAlbum);
	}

	@GetMapping("/albums")
	public List<MusicAlbum> getMusicAlbums() {

		return jukeBoxService.getAllMusicAlbums();
	}

	@GetMapping("/albums/musicians/{musicianId}")
	public List<MusicAlbum> getMusicAlbumsByMusician(@PathVariable("musicianId") Long musicianId) {

		return jukeBoxService.getMusicAlbumsByMusician(musicianId);
	}

	@PostMapping("/musicians")
	public Musician addMusician(@RequestBody Musician musician) {

		return jukeBoxService.addMusician(musician);
	}

	@PutMapping("/musicians/{musicianId}")
	public Musician updateMusician(@PathVariable("musicianId") Long musicianId, @RequestBody Musician musician) {

		return jukeBoxService.updateMusician(musicianId, musician);
	}

	@GetMapping(path = "/musicians/albums/{albumId}")
	public List<Musician> getMusiciansByMusicAlbum(@PathVariable("albumId") Long musicAlbumId) {

		return jukeBoxService.getMusiciansByMusicAlbum(musicAlbumId);
	}
}
