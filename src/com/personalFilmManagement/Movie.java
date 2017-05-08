package com.personalFilmManagement;


public class Movie {
	private String 	ENGname;
	private String	CHSname;
	private String 	releaseDate;
	private String 	physicalAddress;
	private Resolution resolution;
	private	CompressionScheme	compressionScheme;
	private VideoCoding	videoCoding;
	private	AudioCoding	audioCoding;
	
	public Movie(String eNGname, String cHSname, String releaseDate, String physicalAddress, Resolution resolution,
			CompressionScheme compressionScheme, VideoCoding videoCoding, AudioCoding audioCoding) {
		super();
		ENGname = eNGname;
		CHSname = cHSname;
		this.releaseDate = releaseDate;
		this.physicalAddress = physicalAddress;
		this.resolution = resolution;
		this.compressionScheme = compressionScheme;
		this.videoCoding = videoCoding;
		this.audioCoding = audioCoding;
	}
	public Movie(){
		this.releaseDate = null;;
		this.physicalAddress = null;
		this.resolution = null;
		this.compressionScheme = null;
		this.videoCoding = null;
		this.audioCoding = null;
	}
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return ENGname+CHSname+releaseDate+physicalAddress
				+resolution.name()+compressionScheme.name()+
				videoCoding.name()+audioCoding.name();
	}
	public String getENGname() {
		return ENGname;
	}
	public void setENGname(String eNGname) {
		ENGname = eNGname;
	}
	public String getCHSname() {
		return CHSname;
	}
	public void setCHSname(String cHSname) {
		CHSname = cHSname;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	public Resolution getResolution() {
		return resolution;
	}
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	public CompressionScheme getCompressionScheme() {
		return compressionScheme;
	}
	public void setCompressionScheme(CompressionScheme compressionScheme) {
		this.compressionScheme = compressionScheme;
	}
	public VideoCoding getVideoCoding() {
		return videoCoding;
	}
	public void setVideoCoding(VideoCoding videoCoding) {
		this.videoCoding = videoCoding;
	}
	public AudioCoding getAudioCoding() {
		return audioCoding;
	}
	public void setAudioCoding(AudioCoding audioCoding) {
		this.audioCoding = audioCoding;
	}
	

}
