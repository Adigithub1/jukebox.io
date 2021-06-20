package com.aditya.jukeboxapplication.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "musician")
public class Musician {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "musician_name")
	private String musicianName;
	@Column(name = "musician_type")
	private String musicianType;
	@JsonIgnore
	@ManyToMany(mappedBy = "musicians", fetch = FetchType.LAZY)
	private Set<MusicAlbum> musicAlbums;

	public Musician() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Musician(Long id, String musicianName, String musicianType, Set<MusicAlbum> musicAlbums) {
		super();
		this.id = id;
		this.musicianName = musicianName;
		this.musicianType = musicianType;
		this.musicAlbums = musicAlbums;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMusicianName() {
		return musicianName;
	}

	public void setMusicianName(String musicianName) {
		this.musicianName = musicianName;
	}

	public String getMusicianType() {
		return musicianType;
	}

	public void setMusicianType(String musicianType) {
		this.musicianType = musicianType;
	}

	public Set<MusicAlbum> getMusicAlbums() {
		return musicAlbums;
	}

	public void setMusicAlbums(Set<MusicAlbum> musicAlbums) {
		this.musicAlbums = musicAlbums;
	}

	@Override
	public String toString() {
		return "Musician [id=" + id + ", musicianName=" + musicianName + ", musicianType=" + musicianType
				+ ", musicAlbums=" + musicAlbums + "]";
	}

}
