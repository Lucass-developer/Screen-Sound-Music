package br.com.desafios.ScreenSoundMusic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.desafios.ScreenSoundMusic.main.Main;
import br.com.desafios.ScreenSoundMusic.repository.AlbumRepository;
import br.com.desafios.ScreenSoundMusic.repository.ArtistaRepository;
import br.com.desafios.ScreenSoundMusic.repository.MusicaRepository;

@SpringBootApplication
public class ScreenSoundMusicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenSoundMusicApplication.class, args);
	}

	//Repositories
	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired
	private MusicaRepository musicaRepository;
	@Autowired
	private AlbumRepository albumRepository;

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(artistaRepository, musicaRepository, albumRepository);
		main.menu();
	}

}
