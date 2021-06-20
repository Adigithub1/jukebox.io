package com.aditya.jukeboxapplication.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "music_album")
public class MusicAlbum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "album_name")
	private String albumName;
	@Column(name = "description")
	private String description;
	@Column(name = "release_date")
	private Date releaseDate;
	@Column(name = "genre")
	private String genre;
	@Column(name = "price")
	private Integer price;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "music_albums_musicians", joinColumns = {
			@JoinColumn(name = "music_album_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "musician_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Musician> musicians;

	public MusicAlbum() {
	}

	public MusicAlbum(Long id, String albumName, String description, Date releaseDate, String genre, Integer price,
			Set<Musician> musicians) {
		super();
		this.id = id;
		this.albumName = albumName;
		this.description = description;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.price = price;
		this.musicians = musicians;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Set<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(Set<Musician> musicians) {
		this.musicians = musicians;
	}

	@Override
	public String toString() {
		return "MusicAlbum [id=" + id + ", albumName=" + albumName + ", description=" + description + ", releaseDate="
				+ releaseDate + ", genre=" + genre + ", price=" + price + ", musicians=" + musicians + "]";
	}

}
