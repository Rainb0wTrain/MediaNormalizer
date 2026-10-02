import java.nio.file.Path;

public class AnimeInfo extends MediaInfo{
    private final int season;
    private final int episode;

    //jellyfin has no dub/sub naming spec yet

    public AnimeInfo(String title, String extension, int season, int episode) {
        super(title, extension);
        this.season = season;
        this.episode = episode;
    }

    @Override
    public Path getJellyfinPath() {
        //anime file path -
        //folder (title of show)
        //folder (Season #)
        //file"."extension (S##E###.mkv)

        String seasonFolder = "Season " + String.format("%02d", season);
        String fileName = title + String.format(" - S%02dE%03d", season, episode);


        return Path.of(title, seasonFolder, fileName + "." + extension);
    }

    @Override
    public String getLibraryFolder() {
        return "TV Shows";
    }


}

